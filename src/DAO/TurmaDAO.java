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
        List<Turma> turmas = new ArrayList<>();
        String sql = "SELECT id, nome FROM Turmas";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = Conexao.getConexao();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Turma turma = new Turma(nome);
                turma.setId(id);

                turmas.add(turma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return turmas;
    }
}
