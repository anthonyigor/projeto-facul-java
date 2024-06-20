package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import entity.Aluno;
import entity.Turma;

public class AlunoDAO {
    
    public void cadastrarAluno(Aluno aluno) {
        String sql = "INSERT INTO ALUNOS (nome, matricula, login, senha) VALUES (?, ?, ?, ?)";
    
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getMatricula());
            ps.setString(3, aluno.getLogin());
            ps.setString(4, aluno.getSenha());
    
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
    

    public void atribuirTurma(Turma turma, Aluno aluno) {
        String sql = "UPDATE ALUNOS SET TURMA_ID = ? WHERE MATRICULA = ?";
    
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, turma.getId());
            ps.setString(2, aluno.getMatricula());
    
            ps.executeUpdate();
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
    
}
