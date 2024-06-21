import java.util.List;
import java.util.Scanner;

import DAO.AlunoDAO;
import DAO.DisciplinaDAO;
import DAO.TurmaDAO;
import DAO.TurmaDisciplinaDAO;
import entity.Aluno;
import entity.Disciplina;
import entity.Turma;
import entity.TurmaDisciplina;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            // Menu
            System.out.println("\nBem vindo(a) ao nosso sistema, o que deseja fazer hoje? \n[1] Cadastrar aluno \n[2] Cadastrar turma \n[3] Cadastrar disciplina \n[4] Lançar nota de aluno"); 
            System.out.println("[5] Listar Alunos \n[6] Listar Turmas \n[7] Visualizar notas do aluno \n[8] Sair");
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
                        if(turmas.size() != 0){
                            for (Turma turma : turmas) {
                                System.out.printf("[%d] %s", turma.getId(), turma.getNome());
                                System.out.println();
                            }
                        }else{
                            System.out.println("Nenhuma turma disponivel, Cadastre uma turma primeiro");
                            Thread.sleep(3000);
                            break;
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
                            Thread.sleep(3000);
                        } else {
                            System.out.println("Opção inválida!");
                            Thread.sleep(3000);
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
                
                case 3:
                    System.out.println("Selecione a turma para casdastrar disciplina:");
                    try {
                        List<Turma> turmas = new TurmaDAO().getTurmas();
                        if(turmas.size() != 0){
                            for (Turma turma : turmas) {
                                System.out.printf("[%d] %s", turma.getId(), turma.getNome());
                                System.out.println();
                            }
                        }else{
                            System.out.println("Nenhuma turma disponivel. Cadastre uma turma primeiro");
                            Thread.sleep(3000);
                            break;
                        }
                        int turmaInput = scanner.nextInt();
                        scanner.nextLine();

                        Turma turmaSelecionada = null;
                        for (Turma turma : turmas) {
                            if (turma.getId() == turmaInput) {
                                turmaSelecionada = turma;
                            }
                        }

                        System.out.println("Digite o nome da Disciplina:");
                        String nomeDisciplina = scanner.nextLine();

                        Disciplina d = new Disciplina(nomeDisciplina);

                        try {
                            new DisciplinaDAO().cadastrarDiscplina(d);
                            System.out.printf("Disciplina %s criada com sucesso!", nomeDisciplina);
                            System.out.println();
                            try {
                                Disciplina lastdisciplina = new DisciplinaDAO().getLastDisciplina();
                                TurmaDisciplina td = new TurmaDisciplina(turmaSelecionada.getId(), lastdisciplina.getId());

                                new TurmaDisciplinaDAO().criarTurmaDisciplina(td);
                                System.out.printf("Disciplina %s foi cadastrada na turma %s com Sucesso", turmaSelecionada.getNome(), lastdisciplina.getNome());
                                System.out.println();
                                Thread.sleep(3000);
                                
                            } catch (Exception e) {
                                System.out.println("Ocorreu um erro ao atribuir disciplina");
                            }
                        } catch (Exception e) {
                            System.out.println("Ocorreu um erro ao criar disciplina");
                        }

                        break;


                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro cadastrar disciplina!");
                    }
                    break;

                case 5:
                    try {
                        List<Aluno> alunos = new AlunoDAO().getAlunos();
                        System.out.println("Lista de Alunos Cadastrados");
                            if(alunos.size() != 0){
                                TurmaDAO turmaDAO = new TurmaDAO();
                                Turma turma = null;
                                for (Aluno aluno : alunos) {
                                    turma = turmaDAO.getTurmaProcurada(aluno.getTurma_id());
                                    System.out.printf("Nome: %s, Matricula: %s, Turma: %s", aluno.getNome(), aluno.getMatricula(), turma.getNome());
                                    System.out.println();
                                }
                                Thread.sleep(3000);
                            }else{
                                System.out.println("Nenhuma Aluno Cadastrado!");
                                Thread.sleep(3000);
                                break;
                            }
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro ao lista os Alunos!");
                        System.out.println(e);
                    }
                    break;

                case 6:
                    List<Turma> turmas = new TurmaDAO().getTurmas();
                    if(turmas.size() != 0){
                        for (Turma turma : turmas) {
                            System.out.printf("Turma: %s", turma.getNome());
                            System.out.println();
                        }
                        Thread.sleep(3000);
                    }else{
                        System.out.println("Nenhuma turma disponivel, Cadastre uma turma primeiro");
                        Thread.sleep(3000);
                        break;
                    }
                    break;
                case 8:
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
