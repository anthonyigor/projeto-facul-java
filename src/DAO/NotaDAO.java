package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.Conexao;
import entity.Nota;

public class NotaDAO {
    public void casdastrarNota(Nota nota){
        String sql = "INSERT INTO NOTAS (aluno_id, turma_disciplina_id, nota) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, nota.getAlunoId());
            ps.setInt(2, nota.getTurmaDisciplinaId());
            ps.setDouble(3, nota.getNota());


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

    public Double getNotaByAlunoIdAndDisciplinaId(int alunoId, int disciplinaId) {
        String sql = "SELECT n.nota " +
                     "FROM Notas n " +
                     "JOIN Alunos a ON n.aluno_id = a.id " +
                     "JOIN Turmas_Disciplinas td ON n.turma_disciplina_id = td.id " +
                     "JOIN Disciplinas d ON td.disciplina_id = d.id " +
                     "WHERE a.id = ? AND d.id = ?";

        Double nota = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, alunoId);
            ps.setInt(2, disciplinaId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nota = rs.getDouble("nota");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nota;
    }
}
