package entities;


import javax.swing.text.Document;

public class Livre implements Document {

    protected int nbPages;

    protected Abonne reservePar = null;
    protected Abonne empruntePar = null;
    protected long dateReservation;

    public Livre(String id, String titre, int nbPages) {
        super();
        this.nbPages = nbPages;
    }

    public Livre() {

    }

    protected void emprunt(Abonne ab) {
    }

    protected void reservePar(Abonne ab) {

    }
}