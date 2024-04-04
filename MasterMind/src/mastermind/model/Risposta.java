package mastermind.model;

public class Risposta {
    private int dim;
    private PioloRisposta risposta[];

    public Risposta(int dim) {
        this.dim = dim;
        this.risposta = new PioloRisposta[dim];
        for (int i = 0; i < dim; i++) {
            this.risposta[i] = PioloRisposta.VUOTO;
        }
    }

    public boolean equals(Risposta that) {
        if (this.dim != that.dim)
            return false;
        for (int i = 0; i < dim; i++) {
            if (this.risposta[i] != that.risposta[i]) {
                return false;
            }
        }
        return true;
    }
    public int dim() {
        return dim;
    }
    public PioloRisposta getPiolo(int index) {
        if (index >= 0 && index < dim) {
            return risposta[index];
        } else {
            return null;
        }
    }

    public void setPiolo(int index, PioloRisposta pr) {
        if (index >= 0 && index < dim) {
            risposta[index] = pr;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dim; i++) {
            builder.append(risposta[i]).append(",");
        }
        // Rimuovi l'ultima virgola
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 1);
        }
        return builder.toString();
    }

    public boolean vittoria() {
        for (int i = 0; i < dim; i++) {
            if (risposta[i] != PioloRisposta.NERO) {
                return false;
            }
        }
        return true;
    }
}
