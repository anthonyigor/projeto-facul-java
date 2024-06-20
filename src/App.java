import java.util.List;
import java.util.Scanner;

import DAO.AlunoDAO;
import DAO.TurmaDAO;
import entity.Aluno;
import entity.Turma;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        //menu
        System.out.println("Bem vindo(a) ao nosso sistema, o que deseja fazer hoje? \n[1] Cadastrar aluno \n[2] Cadastrar turma \n[3] Cadastrar disciplina \n[4] Visualizar notas do aluno \n[5] Visualizar turma");
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

                    System.out.println("Deseja adicionar " + nome + " em uma turma? [S/N]");
                    String adicionarTurma = scanner.nextLine();

                   switch (adicionarTurma) {
                    case "S":
                        System.out.println("Escolha a turma entre as descritas abaixo:");
                        List<Turma> turmas = new TurmaDAO().getTurmas();
                        for (Turma turma : turmas) {
                            System.out.println(turma.getId() + "-" + turma.getNome());
                        }
                        break;
                   
                    default:
                        break;
                   }

                } catch (Exception e) {
                    System.out.println("Algo deu errado");
                }
                break;
            default:
                break;
        }

    }
}
