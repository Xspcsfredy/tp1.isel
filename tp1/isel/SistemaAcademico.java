package tp1.isel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaAcademico {

    private final List<Aluno> alunos;
    private final List<Docente> docentes;
    private final List<UnidadeCurricular> ucs;
    private final List<Inscricao> inscricoes;
    private final List<Avaliacao> avaliacoes;

    public SistemaAcademico() {
        alunos = new ArrayList<>();
        docentes = new ArrayList<>();
        ucs = new ArrayList<>();
        inscricoes = new ArrayList<>();
        avaliacoes = new ArrayList<>();
    }

    // 1 - REGISTAR ALUNO
    public boolean registarAluno(int numero, String nome, String email, String curso, int ano) {
        if (procurarAluno(numero) != null) {
            return false;
        }
        if (!emailValido(email) || ano < 1 || ano > 3) {
            return false;
        }
        alunos.add(new Aluno(numero, nome, email, curso, ano));
        return true;
    }

    // 2 - REMOVER ALUNO
    public boolean removerAluno(int numero) {

        for (int i = 0; i < alunos.size(); i++) { // Percorre a lista de alunos

            Aluno a = alunos.get(i); // Vai buscar o do indice

            if (a.getNumero() == numero) { // Compara o número inserido
                alunos.remove(i); // Remove da lista
                return true;
            }
        }

        return false;
    }

    // 3 - LISTAR ALUNOS
    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos);
    }

    // 4 - LANÇAR AVALIAÇÃO
    public boolean lancarAvaliacao(int numeroAluno, String codigoUc, String elementoAvaliacao, double nota, double peso) {
        Aluno aluno = procurarAluno(numeroAluno);
        UnidadeCurricular uc = procurarUc(codigoUc);

        if (aluno == null || uc == null) { // Se não houver aluno ou uc
            return false;
        }
        if (!jaInscrito(numeroAluno, uc.getCodigo())) { // Se o aluno não está inscrito
            return false;
        }
        if (nota < 0.0 || nota > 20.0 || peso <= 0.0 || peso > 100.0) { // Se notas/pesos invalidos
            return false;
        }

        double pesoAtual = 0.0;

        for (Avaliacao a : avaliacoes) {
            if (a.getNumeroAluno() == numeroAluno && a.getCodigoUc().equals(uc.getCodigo())) {
                pesoAtual += a.getPeso();
            }
        }
        if (pesoAtual + peso > 100.0) {
            return false;
        }

        Avaliacao nova = new Avaliacao(numeroAluno, uc.getCodigo(), elementoAvaliacao, nota, peso);
        avaliacoes.add(nova);
        return true;
    }

    // 5 - CALCULAR NOTA FINAL
    public Double calcularNotaFinal(int numeroAluno, String codigoUc) {
        String chaveUc = codigoUc.toUpperCase();
        double somaPesos = 0.0;
        double somaPonderada = 0.0;

        for (Avaliacao a : avaliacoes) {
            if (a.getNumeroAluno() == numeroAluno && a.getCodigoUc().equals(chaveUc)) {
                somaPesos += a.getPeso();
                somaPonderada += a.getNota() * a.getPeso();
            }
        }

        if (somaPesos == 0.0) {
            return null;
        }

        return somaPonderada / somaPesos;
    }

    // 6 - REGISTAR DOCENTE
    public boolean registarDocente(int id, String nome, String email, String departamento) {
        if (procurarDocente(id) != null) {
            return false;
        }
        if (!emailValido(email)) {
            return false;
        }
        docentes.add(new Docente(id, nome, email, departamento));
        return true;
    }

    // 7 - REMOVER DOCENTE
    public boolean removerDocente(int id) {

        for (int i = 0; i < docentes.size(); i++) { // Percorre a lista de alunos

            Docente d = docentes.get(i); // Vai buscar o do indice

            if (d.getId() == id) { // Compara o número inserido
                docentes.remove(i); // Remove da lista
                return true;
            }
        }
        return false;
    }

    // 8 - CRIAR UC
    public boolean criarUc(String codigo, String nome, int ects, int semestre, int capacidade) {
        if (procurarUc(codigo) != null) {
            return false;
        }
        if (ects <= 0 || capacidade <= 0 || (semestre != 1 && semestre != 2)) {
            return false;
        }
        ucs.add(new UnidadeCurricular(codigo.toUpperCase(), nome, ects, semestre, capacidade));
        return true;
    }

    // 9 - INSCREVER ALUNO
    public boolean inscreverAluno(int numeroAluno, String codigoUc) {
        Aluno aluno = procurarAluno(numeroAluno);
        UnidadeCurricular uc = procurarUc(codigoUc);

        if (aluno == null || uc == null) {
            return false;
        }
        if (jaInscrito(numeroAluno, uc.getCodigo())) {
            return false;
        }
        if (contarInscritosUc(uc.getCodigo()) >= uc.getCapacidade()) {
            return false;
        }

        inscricoes.add(new Inscricao(numeroAluno, uc.getCodigo(), LocalDate.now()));
        return true;
    }

    // 10 - ANULAR INSCRIÇÃO
    public boolean anularInscricao(int numeroAluno, String codigoUc) {
        for (int i = 0; i < inscricoes.size(); i++) { // Percorre a lista de inscrições

            Inscricao insc = inscricoes.get(i); // Vai buscar a do indice

            if (insc.getNumeroAluno() == numeroAluno && insc.getCodigoUc().equals(codigoUc)) { // Compara os dados
                inscricoes.remove(i); // Remove da lista
                return true;
            }
        }
        return false;
    }

    // 11 - LISTAR UCS
    public List<UnidadeCurricular> listarUcs() {
        return new ArrayList<>(ucs);
    }

    // 12 - GERAR RELATORIO DA UC
    public RelatorioUc gerarRelatorioUc(String codigoUc) {

        UnidadeCurricular uc = procurarUc(codigoUc);

        if (uc == null) { // Se não houver UCs
            return null;
        }

        int inscritos = 0;
        int avaliados = 0;
        int aprovados = 0;
        int reprovados = 0;

        double somaNotas = 0;

        for (Inscricao i : inscricoes) {

            if (i.getCodigoUc().equals(codigoUc)) {

                inscritos++;

                Double nota = calcularNotaFinal(i.getNumeroAluno(), codigoUc);

                if (nota != null) {

                    avaliados++;
                    somaNotas = somaNotas + nota;

                    if (nota >= 9.5) {
                        aprovados++;
                    } else {
                        reprovados++;
                    }
                }
            }
        }

        double media = 0;

        if (avaliados > 0) {
            media = somaNotas / avaliados;
        }

        return new RelatorioUc(codigoUc, inscritos, avaliados, media, aprovados, reprovados);
    }

    // FUNÇÕES QUE N SAO DO MENU
    // PROCURAR ALUNO
    private Aluno procurarAluno(int numero) {
        for (Aluno a : alunos) {
            if (a.getNumero() == numero) {
                return a;
            }
        }
        return null;
    }

    // PROCURAR DOCENTE
    private Docente procurarDocente(int id) {
        for (Docente d : docentes) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    private UnidadeCurricular procurarUc(String codigo) {
        String chave = codigo.toUpperCase();
        for (UnidadeCurricular uc : ucs) {
            if (uc.getCodigo().equals(chave)) {
                return uc;
            }
        }
        return null;
    }

    private boolean jaInscrito(int numeroAluno, String codigoUc) {
        for (Inscricao i : inscricoes) {
            if (i.getNumeroAluno() == numeroAluno && i.getCodigoUc().equals(codigoUc)) {
                return true;
            }
        }
        return false;
    }

    private int contarInscritosUc(String codigoUc) {
        int contador = 0;
        for (Inscricao i : inscricoes) {
            if (i.getCodigoUc().equals(codigoUc)) {
                contador++;
            }
        }
        return contador;
    }

    private boolean emailValido(String email) {
        return (email != null && email.contains("@") && email.contains("."));
    }

    public Avaliacao getUltimaAvaliacao() {
        if (avaliacoes.isEmpty()) {
            return null;
        }

        return avaliacoes.get(avaliacoes.size() - 1);
    }

    public Inscricao getUltimaInscricao() {
        if (inscricoes.isEmpty()) {
            return null;
        }

        return inscricoes.get(inscricoes.size() - 1);
    }
}