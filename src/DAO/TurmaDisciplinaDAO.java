package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import entity.TurmaDisciplina;


public class TurmaDisciplinaDAO {
    public void criarTurmaDisciplina(TurmaDisciplina td){
        String sql = "INSERT INTO Turmas_Disciplinas (turma_id, disciplina_id) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, td.getTurmaId());
            ps.setInt(2, td.getDisciplinaId());

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
