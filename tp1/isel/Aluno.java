package tp1.isel;

public class Aluno {

    // Atributos
    private final int numero;
    private String nome;
    private String email;
    private String curso;
    private int ano;

    // Construtor da classe
    public Aluno(int numero, String nome, String email, String curso, int ano) {
        this.numero = numero;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.ano = ano;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCurso() {
        return curso;
    }

    public int getAno() {
        return ano;
    }

    @Override
    public String toString() {
        return "Aluno Número = " + numero + ", Nome = " + nome + ", Email = " + email + ", Curso = " + curso + ", Ano = " + ano;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Aluno))
            return false;

        Aluno a = (Aluno) o;
        return numero == a.numero;
    }
}
