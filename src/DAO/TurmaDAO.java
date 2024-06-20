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
}
