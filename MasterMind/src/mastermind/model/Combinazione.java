package mastermind.model;

import java.util.StringJoiner;

public class Combinazione {
    private int dim;
    private PioloDiGioco combinazione[];
    
    public Combinazione(int dim) {
        this.dim = dim;
        this.combinazione = new PioloDiGioco[dim];
    }

    public int dim() {
        return dim;
    }
    
    public boolean equals(Combinazione that) {
        if (this.dim != that.dim)
            return false;
        for (int i = 0; i < dim; i++) {
            if (!this.combinazione[i].equals(that.combinazione[i])) {
                return false;
            }
        }
        return true;
    }

    public PioloDiGioco getPiolo(int index) {
        if (index >= 0 && index < dim) {
            return combinazione[index];
        } else {
            return null;
        }
    }

    public void setPiolo(int index, PioloDiGioco c) {
        if (index >= 0 && index < dim) {
            combinazione[index] = c;
        } else {
            //error
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < dim; i++) {
            joiner.add(combinazione[i].toString());
        }
        return joiner.toString().toUpperCase();
    }
}
