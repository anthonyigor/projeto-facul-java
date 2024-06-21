package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entity.Turma;

public class TurmaDAO {

     public void cadastrarTurma(Turma turma) {
        String sql = "INSERT INTO TURMAS (nome) VALUES (?)";
    
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, turma.getNome());
    
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<Turma> getTurmas() {
        String sql = "SELECT id, nome FROM TURMAS";
        List<Turma> turmas = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Turma turma = new Turma(rs.getString("nome"));
                turma.setId(rs.getInt("id"));
                turmas.add(turma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turmas;
    }

    public Turma getTurmaProcurada(Integer id) {
        String sql = "SELECT * FROM Turmas WHERE id = ?";
        Turma turma = null;

        try (Connection conn = Conexao.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    turma = new Turma(rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return turma;
    }

}
