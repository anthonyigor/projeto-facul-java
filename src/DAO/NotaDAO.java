package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
