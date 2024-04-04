package mastermind.model;

public class Gioco {
    private int dim, maxTentativi;
    private Risposta risposte[];
    private Status status;
    private Combinazione tentativi[];
    private int currentAttemptIndex; 

    public Gioco(int maxTentativi, int dim) {
        this.dim = dim;
        this.maxTentativi = maxTentativi;
        this.currentAttemptIndex = 0;
        this.risposte = new Risposta[maxTentativi];
        this.status = Status.IN_CORSO; 
        this.tentativi = new Combinazione[maxTentativi];
    }
    
    public int maxTentativi() {
        return maxTentativi;
    }
    
    public Status registraMossa(Combinazione tentativo, Risposta risposta) {
        if (status != Status.IN_CORSO || currentAttemptIndex >= maxTentativi) {
            return status;
        }
        
        tentativi[currentAttemptIndex] = tentativo;
        risposte[currentAttemptIndex] = risposta;
        currentAttemptIndex++;
        
        PioloRisposta[] soluzione = {
            PioloRisposta.BIANCO,
            PioloRisposta.NERO,
            PioloRisposta.VUOTO
        };
        
        // Utilizzare il metodo vittoria() per verificare la condizione di vittoria
        if (risposta.vittoria()) {
            return Status.VITTORIA;
        }
        
        if (currentAttemptIndex >= maxTentativi) {
            return Status.PERSO;
        }
        
        return Status.IN_CORSO;
    }
    
    public int tentativiEffettuati() {
        return currentAttemptIndex;
    }
    
    public int tentativiRimasti() {
        return maxTentativi - currentAttemptIndex;
    }
    
    public Combinazione tentativo(int index) {
        if (index >= 0 && index < currentAttemptIndex) {
            return tentativi[index];
        } else {
            return null;
        }
    }
    
    public Risposta risposta(int index) {
        if (index >= 0 && index < currentAttemptIndex) {
            return risposte[index];
        } else {
            return null;
        }
    }
    
    public Status stato() {
        return status;
    }
    
    public int dimensione() {
        return dim;
    }
    
    public Combinazione ultimoTentativo() {
        if (currentAttemptIndex > 0) {
            return tentativi[currentAttemptIndex - 1];
        } else {
            return null;
        }
    }

    public Risposta ultimaRisposta() {
        if (currentAttemptIndex > 0) {
            return risposte[currentAttemptIndex - 1];
        } else {
            return null;
        }
    }

    public String situazione() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < currentAttemptIndex; i++) {
            builder.append(i + 1).append(") ").append(tentativi[i]).append("\t\t");
            builder.append(risposte[i]).append("\n");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Situazione:\n");
        builder.append(situazione());
        builder.append("Gioco: ").append(status).append("\n");
        return builder.toString();
    }
}
