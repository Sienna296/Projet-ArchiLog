package entities;


import javax.swing.text.Document;

public class Livre implements Document {
    private String id;
    private String titre;
    protected Abonne reservePar = null;
    protected Abonne empruntePar = null;
    protected long dateReservation;

    public Livre(String id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    @Override
    public String idDoc() { return id; }

    @Override
    public synchronized void reservation(Abonne ab) throws Exception {
        if (reservePar != null || empruntePar != null) {
            throw new Exception("ce livre est déjà réservé ou emprunté");
        }
        this.reservePar = ab;
        this.dateReservation = System.currentTimeMillis();
    }

    @Override
    public synchronized void emprunt(Abonne ab) throws Exception {
        if (empruntePar != null) {
            throw new Exception("ce livre est déjà emprunté");
        }
        if (reservePar != null && reservePar.getNumero() != ab.getNumero()) {
            throw new Exception("ce livre est réservé pour un autre");
        }
        this.empruntePar = ab;
        this.reservePar = null;
    }

    @Override
    public synchronized void retour() throws Exception {
        this.empruntePar = null;
        this.reservePar = null;
    }
}