package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entity.Disciplina;


public class DisciplinaDAO {
    public void cadastrarDiscplina(Disciplina disciplina){
        String sql = "INSERT INTO Disciplinas (nome) VALUES (?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, disciplina.getNome());

            ps.execute();
        } catch (Exception e) {
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

    public List<Disciplina> getDisciplinas() {
        String sql = "SELECT id, nome FROM DISCIPLINAS";
        List<Disciplina> disciplinas = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina(rs.getString("nome"));
                disciplina.setId(rs.getInt("id"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinas;
    }

    public Disciplina getLastDisciplina() {
        String sql = "SELECT id, nome FROM DISCIPLINAS ORDER BY id DESC LIMIT 1";
        Disciplina disciplina = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                disciplina = new Disciplina(rs.getString("nome"));
                disciplina.setId(rs.getInt("id"));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplina;
    }

    public Disciplina getDisciplinaProcurada(Integer id) {
        String sql = "SELECT id, nome FROM DISCIPLINAS WHERE id = ?";
        Disciplina disciplina = null;
    
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    disciplina = new Disciplina(rs.getString("nome"));
                    disciplina.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return disciplina;
    }
    
}