import java.util.List;
import java.util.Scanner;

import DAO.AlunoDAO;
import DAO.TurmaDAO;
import entity.Aluno;
import entity.Turma;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            // Menu
            System.out.println("\nBem vindo(a) ao nosso sistema, o que deseja fazer hoje? \n[1] Cadastrar aluno \n[2] Cadastrar turma \n[3] Cadastrar disciplina \n[4] Lançar nota de aluno \n[5] Visualizar notas do aluno \n[6] Visualizar turma \n[7] Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Informe o nome do aluno:");
                    String nome = scanner.nextLine();
                    
                    System.out.println("Informe o login do aluno:");
                    String login = scanner.nextLine();
                    
                    System.out.println("Crie uma senha para o aluno:");
                    String senha = scanner.nextLine();
                    
                    Aluno a = new Aluno(nome, login, senha);

                    try {
                        AlunoDAO aluno_dao = new AlunoDAO();

                        aluno_dao.cadastrarAluno(a);
                        System.out.println("Aluno(a) " + nome + " criado com sucesso");

                        System.out.println("Selecione a turma que esse aluno fará parte:");
                        List<Turma> turmas = new TurmaDAO().getTurmas();
                        for (Turma turma : turmas) {
                            System.out.printf("[%d] %s", turma.getId(), turma.getNome());
                            System.out.println();
                        }
                        int turmaInput = scanner.nextInt();
                        scanner.nextLine();
                        
                        Turma turmaSelecionada = null;
                        for (Turma turma : turmas) {
                            if (turma.getId() == turmaInput) {
                                turmaSelecionada = turma;
                            }
                        }

                        if (turmaSelecionada != null) {
                            aluno_dao.atribuirTurma(turmaSelecionada, a);
                            System.out.printf("Aluno criado e adicionado a turma %s", turmaSelecionada.getNome());
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    } catch (Exception e) {
                        System.out.println("Algo deu errado");
                    }
                    break;
                
                case 2:
                    System.out.println("Informe o nome da turma a ser criada:");
                    String nomeTurma = scanner.nextLine();

                    Turma t = new Turma(nomeTurma);
                    try {
                        new TurmaDAO().cadastrarTurma(t);
                        System.out.printf("Turma %s criada com sucesso!", nomeTurma);
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro ao criar turma");
                    }
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        }
        scanner.close();
    }
}
