package entities;


import javax.swing.text.Document;

public class Livre implements Document {

    protected Abonne reservePar = null;
    protected Abonne empruntePar = null;
    protected long dateReservation;

    public Livre(String id, String titre) {
        super();
    }

}