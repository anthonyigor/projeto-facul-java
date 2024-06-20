import java.util.Scanner;

import DAO.AlunoDAO;
import entity.Aluno;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        //menu
        System.out.println("Bem vindo(a) ao nosso sistema, o que deseja fazer hoje? \n[1] Cadastrar aluno");
        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                Aluno a = new Aluno();

                System.out.println("Informe o nome do aluno:");
                String nome = scanner.nextLine();
                a.setNome(nome);
                
                System.out.println("Informe a matrícula do aluno:");
                String matricula = scanner.nextLine();
                a.setMatricula(matricula);

                System.out.println("Informe o login do aluno:");
                String login = scanner.nextLine();
                a.setLogin(login);

                System.out.println("Crie uma senha para o aluno:");
                String senha = scanner.nextLine();
                a.setSenha(senha);
                
                try {
                    new AlunoDAO().cadastrarAluno(a);
                    System.out.println("Aluno(a) " + nome + " criado com sucesso");
                } catch (Exception e) {
                    System.out.println("Algo deu errado");
                }
                break;
            default:
                break;
        }

    }
}
