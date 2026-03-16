package tp1.isel;

import java.util.List;
import java.util.Scanner;

public class AppISEL {

    private static final SistemaAcademico sistema = new SistemaAcademico();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
        scanner.close();
    }

    private static void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n                                                                               ▄▄  ▄                                                  \n" +
                    "                                                                              ▀  ▀▀                                                   \n" +
                    "██▄  ▄██ ██████ ███  ██ ██  ██   ████▄  ██████    ▄████  ██████ ▄█████ ██████ ▄████▄ ▄████▄   ████▄  ▄████▄   ██ ▄█████ ██████ ██     \n" +
                    "██ ▀▀ ██ ██▄▄   ██ ▀▄██ ██  ██   ██  ██ ██▄▄     ██  ▄▄▄ ██▄▄   ▀▀▀▄▄▄   ██   ██▄▄██ ██  ██   ██  ██ ██  ██   ██ ▀▀▀▄▄▄ ██▄▄   ██     \n" +
                    "██    ██ ██▄▄▄▄ ██   ██ ▀████▀   ████▀  ██▄▄▄▄    ▀███▀  ██▄▄▄▄ █████▀   ██   ██  ██ ▀████▀   ████▀  ▀████▀   ██ █████▀ ██▄▄▄▄ ██████ \n" +
                    "                                                                                                                                      ");
            System.out.println("ALUNOS:");
            System.out.println("1. Registar aluno");
            System.out.println("2. Remover aluno");
            System.out.println("3. Listar alunos");
            System.out.println("4. Lançar avaliação");
            System.out.println("5. Calcular nota final");

            System.out.println("\nDOCENTES:");
            System.out.println("6. Registar docentes");
            System.out.println("7. Remover docentes");

            System.out.println("\nUCS:");
            System.out.println("8. Criar UC");
            System.out.println("9. Inscrever aluno em UC");
            System.out.println("10. Anular inscrição");
            System.out.println("11. Listar UCs");
            System.out.println("12. Relatório de UC");

            System.out.println("\n0. Sair");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    registarAluno();
                    break;
                case 2:
                    removerAluno();
                    break;
                case 3:
                    listarAlunos();
                    break;
                case 4:
                    lancarAvaliacao();
                    break;
                case 5:
                    calcularNotaFinal();
                    break;
                case 6:
                    registarDocente();
                    break;
                case 7:
                    removerDocente();
                    break;
                case 8:
                    criarUc();
                    break;
                case 9:
                    inscreverAluno();
                    break;
                case 10:
                    anularInscricao();
                    break;
                case 11:
                    listarUcs();
                    break;
                case 12:
                    relatorioUc();
                    break;
                case 0:
                    System.out.println("A encerrar a aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    // 1 - REGISTAR ALUNO
    private static void registarAluno() {
        int numero = lerInteiro("Numero do aluno: ");
        String nome = lerTexto("Nome: ");
        String email = lerTexto("Email: ");
        String curso = lerTexto("Curso: ");
        int ano = lerInteiro("Ano (1-3): ");

        boolean criado = sistema.registarAluno(numero, nome, email, curso, ano);

        if (criado) {
            System.out.println("Aluno registado com sucesso.");
        } else {
            System.out.println("Não foi possível registar o aluno.");
        }
    }

    // 2 - REMOVER ALUNO
    private static void removerAluno() {
        int numero = lerInteiro("Número do aluno: ");

        boolean removido = sistema.removerAluno(numero);

        if (removido) {
            System.out.println("Aluno removido.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    // 3 - LISTAR ALUNOS
    private static void listarAlunos() {
        List<Aluno> alunos = sistema.listarAlunos();

        if (alunos.isEmpty()) { // Se a lista de alunos estiver vazia
            System.out.println("Nao existem alunos registados.");
            return;
        }

        for (Aluno a : alunos) { // Para cada objeto a do tipo Aluno pertencente à lista alunos
            System.out.println(a);
        }
    }

    // 4 - LANÇAR AVALIAÇÃO
    private static void lancarAvaliacao() {
        int numeroAluno = lerInteiro("Numero do aluno: ");
        String codigoUc = lerTexto("Codigo da UC: ");
        String elementoAvaliacao = lerTexto("Elemento (Teste/Projeto/Exame): ");
        double nota = lerDouble("Nota (0-20): ");
        double peso = lerDouble("Peso (0-100): ");

        boolean lancada = sistema.lancarAvaliacao(numeroAluno, codigoUc, elementoAvaliacao, nota, peso);

        if (lancada) {
            Avaliacao a = sistema.getUltimaAvaliacao();
            System.out.println(a);
        } else {
            System.out.println("Não foi possível registar a avaliação.");
        }
    }

    // 5 - CALCULAR NOTA FINAL
    private static void calcularNotaFinal() {
        int numeroAluno = lerInteiro("Numero do aluno: ");
        String codigoUc = lerTexto("Codigo da UC: ");

        Double nota = sistema.calcularNotaFinal(numeroAluno, codigoUc);

        if (nota == null) {
            System.out.println("Não existem avaliações para este aluno/UC.");
        } else {
            System.out.println("Nota final: " + String.format("%.2f", nota)); // Arredondamento auto. para 2 casas decimais
            if (nota >= 9.5) {
                System.out.println("Resultado: Aprovado");
            } else {
                System.out.println("Resultado: Reprovado");
            }
        }
    }

    // 6 - REGISTAR DOCENTES
    private static void registarDocente() {
        int id = lerInteiro("ID do docente: ");
        String nome = lerTexto("Nome: ");
        String email = lerTexto("Email: ");
        String departamento = lerTexto("Departamento: ");

        boolean criado = sistema.registarDocente(id, nome, email, departamento);

        if (criado) {
            System.out.println("Docente registado.");
        } else {
            System.out.println("Não foi possivel registar o docente.");
        }
    }

    // 7 - REMOVER DOCENTES
    private static void removerDocente() {
        int id = lerInteiro("ID do docente: ");

        boolean removido = sistema.removerDocente(id);

        if (removido) {
            System.out.println("Docente removido.");
        } else {
            System.out.println("Docente não encontrado.");
        }
    }

    // 8 - CRIAR UC
    private static void criarUc() {
        String codigo = lerTexto("Código da UC: ");
        String nome = lerTexto("Nome da UC: ");
        int ects = lerInteiro("ECTS: ");
        int semestre = lerInteiro("Semestre (1-2): ");
        int capacidade = lerInteiro("Capacidade maxima: ");

        boolean criada = sistema.criarUc(codigo, nome, ects, semestre, capacidade);

        if (criada) {
            System.out.println("UC criada com sucesso.");
        } else {
            System.out.println("Não foi possivel criar a UC.");
        }
    }

    // 9 - INSCREVER ALUNO EM UC
    private static void inscreverAluno() {
        int numeroAluno = lerInteiro("Número do aluno: ");
        String codigoUc = lerTexto("Código da UC: ");

        boolean inscrito = sistema.inscreverAluno(numeroAluno, codigoUc);

        if (inscrito) {
            Inscricao i = sistema.getUltimaInscricao();
            System.out.println(i);
        } else {
            System.out.println("Não foi possivel inscrever.");
        }
    }

    // 10 - ANULAR INSCRIÇÃO EM UC
    private static void anularInscricao() {
        int numeroAluno = lerInteiro("Número do aluno: ");
        String codigoUc = lerTexto("Código da UC: ");

        boolean inscrito = sistema.anularInscricao(numeroAluno, codigoUc);

        if (inscrito) {
            System.out.println("Inscrição anulada.");
        } else {
            System.out.println("Inscrição não encontrada.");
        }
    }

    // 11 - LISTAR UCS
    private static void listarUcs() {
        List<UnidadeCurricular> ucs = sistema.listarUcs();

        if (ucs.isEmpty()) { // Se a lista estiver vazia
            System.out.println("Não existem UCs registadas.");
            return;
        }

        for (UnidadeCurricular uc : ucs) {
            System.out.println(uc);
        }
    }

    // 12 - GERAR RELATÓRIO UC
    private static void relatorioUc() {

        System.out.print("Código da UC: ");
        String codigo = scanner.next();

        RelatorioUc r = sistema.gerarRelatorioUc(codigo);

        if (r == null) {
            System.out.println("UC não encontrada.");
            return;
        }

        System.out.println("\n=== RELATÓRIO DA UC ===");
        System.out.println(r);
    }

    // FUNÇÕES AUXILIARES PARA EXCEÇÕES
    private static int lerInteiro(String mensagem) {
        while (true) {
            try { // Bloco para testar / ver se encontra erros, se não encontrar dá return normalmente
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim()); // Converte para inteiro
            } catch (NumberFormatException e) { // Se encontrar erros no formato do número executa este bloco
                System.out.println("Valor inválido. Introduza um número inteiro.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try { // Bloco para testar / ver se encontra erros, se não encontrar dá return normalmente
                System.out.print(mensagem);
                String valor = scanner.nextLine().trim().replace(',', '.');
                return Double.parseDouble(valor); // Converte para double
            } catch (NumberFormatException e) { // Se encontrar erros no formato do número executa este bloco
                System.out.println("Valor inválido. Introduza um número.");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("O valor não pode ser vazio.");
        }
    }
}
