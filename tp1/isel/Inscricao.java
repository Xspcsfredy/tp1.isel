package tp1.isel;

import java.time.LocalDate; // Serve para pegar a data real

public class Inscricao {

    // Atributos
    private final int numeroAluno;
    private final String codigoUc;
    private final LocalDate data;

    // Construtor da classe
    public Inscricao(int numeroAluno, String codigoUc, LocalDate data) {
        this.numeroAluno = numeroAluno;
        this.codigoUc = codigoUc;
        this.data = data;
    }

    public int getNumeroAluno() {
        return numeroAluno;
    }

    public String getCodigoUc() {
        return codigoUc;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Inscrição efetuada com sucesso: " + "Número do aluno = " + numeroAluno + ", código da uc = " + codigoUc + ", data = " + data;
    }
}
