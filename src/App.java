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
            
                System.out.println("Informe o nome do aluno:");
                String nome = scanner.nextLine();
                
                System.out.println("Informe o login do aluno:");
                String login = scanner.nextLine();
                
                System.out.println("Crie uma senha para o aluno:");
                String senha = scanner.nextLine();
                
                Aluno a = new Aluno(nome, login, senha);

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
