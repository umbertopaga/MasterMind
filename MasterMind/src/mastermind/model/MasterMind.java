package mastermind.model;

import java.util.Arrays;

public class MasterMind {
    private Combinazione segreta;
    private int lunghezzaCodice;
    
    public MasterMind(Combinazione segreta) {
        this.segreta = segreta;
        this.lunghezzaCodice = segreta.dim();
    }
    
    public Risposta calcolaRisposta(Combinazione tentativo) {
        int numPioli = lunghezzaCodice;
        Risposta risposta = new Risposta(numPioli);
        PioloRisposta[] risultato = new PioloRisposta[numPioli];
        boolean[] segnalatiSegreta = new boolean[numPioli];
        boolean[] segnalatiTentativo = new boolean[numPioli];

        // Inizializza gli array segnalatiSegreta e segnalatiTentativo
        Arrays.fill(segnalatiSegreta, false);
        Arrays.fill(segnalatiTentativo, false);

        // Conta i pioli di giusto colore e posizione
        int pioliGiusti = 0;
        for (int i = 0; i < numPioli; i++) {
            if (segreta.getPiolo(i).equals(tentativo.getPiolo(i))) {
                risultato[i] = PioloRisposta.NERO;
                segnalatiSegreta[i] = segnalatiTentativo[i] = true;
                pioliGiusti++;
            }
        }

        // Conta i pioli di giusto colore ma in posizione sbagliata
        int pioliSbagliati = 0;
        for (int i = 0; i < numPioli; i++) {
            if (!segnalatiTentativo[i]) {
                for (int j = 0; j < numPioli; j++) {
                    if (!segnalatiSegreta[j] && tentativo.getPiolo(i).equals(segreta.getPiolo(j))) {
                        risultato[i] = PioloRisposta.BIANCO;
                        segnalatiSegreta[j] = segnalatiTentativo[i] = true;
                        pioliSbagliati++;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < numPioli; i++) {
            if (risultato[i] == null) {
                risultato[i] = PioloRisposta.VUOTO;
            }
        }

        for (int i = 0; i < numPioli; i++) {
            risposta.setPiolo(i, risultato[i]);
        }

        return risposta;
    }


    public String combinazioneSegreta() {
        return segreta.toString();
    }

    public MasterMind(int lunghezzaCodice) {
        this.lunghezzaCodice = lunghezzaCodice;
        this.segreta = new Combinazione(lunghezzaCodice);
        sorteggiaCombinazione(segreta);
    }

    public int occorrenze(Combinazione tentativo) {
        int occorrenze = 0;
        for (int i = 0; i < lunghezzaCodice; i++) {
            if (segreta.getPiolo(i).equals(tentativo.getPiolo(i))) {
                occorrenze++;
            }
        }
        return occorrenze;
    }

    private void sorteggiaCombinazione(Combinazione combinazione) {
        for (int i = 0; i < lunghezzaCodice; i++) {
            PioloDiGioco piolo = PioloDiGioco.values()[(int) (Math.random() * PioloDiGioco.values().length)];
            combinazione.setPiolo(i, piolo);
        }
    }
}
