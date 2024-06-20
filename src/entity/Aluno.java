package entity;

public class Aluno {
    private String nome;
    private String matricula;
    private Integer turma_id;
    private String login;
    private String senha;

    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(Integer turma_id) {
        this.turma_id = turma_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

}
