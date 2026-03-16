package tp1.isel;

public class Docente {

    // Atributos
    private final int id;
    private String nome;
    private String email;
    private String departamento;

    // Construtor da classe
    public Docente(int id, String nome, String email, String departamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.departamento = departamento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return "Docente: ID = " + id + ", Nome = " + nome + ", Email = " + email + ", Departamento = " + departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Docente))
            return false;

        Docente d = (Docente) o;
        return id == d.id;
    }
}
