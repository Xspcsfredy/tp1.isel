package tp1.isel;

public class UnidadeCurricular {

    // Atributos
    private final String codigo;
    private final String nome;
    private final int ects;
    private final int semestre;
    private final int capacidade;

    // Construtor de classe
    public UnidadeCurricular(String codigo, String nome, int ects, int semestre, int capacidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.ects = ects;
        this.semestre = semestre;
        this.capacidade = capacidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getEcts() {
        return ects;
    }

    public int getSemestre() {
        return semestre;
    }

    public int getCapacidade() {
        return capacidade;
    }

    @Override
    public String toString() {
        return "UC código " + codigo + ": Nome = " + nome + ", ECTS = " + ects + ", Semestre = " + semestre + ", Capacidade = " + capacidade;
    }
}
