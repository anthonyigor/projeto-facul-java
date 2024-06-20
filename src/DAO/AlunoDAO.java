package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import entity.Aluno;

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
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
