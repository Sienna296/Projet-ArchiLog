package entities;

import java.io.Serializable;
import java.util.Date;

public class Abonne implements Serializable {
    private int numero;
    private String nom;
    private Date dateNaissance;
    private boolean banni = false;

    public Abonne(int numero, String nom, Date dateNaissance) {
        this.numero = numero;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public int getNumero() { return numero; }
    public Date getDateNaissance() { return dateNaissance; }
    public boolean isBanni() { return banni; }
    public void setBanni(boolean banni) { this.banni = banni; }
}