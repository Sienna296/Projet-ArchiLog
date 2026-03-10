package entities;

import java.io.Serializable;
import java.util.Date;

public class Abonne implements Serializable {
    private int numero;
    private String nom;
    private Date dateNaiss;

    public Abonne(int numero, String nom, Date dateNaissance) {
        this.numero = numero;
        this.nom = nom;
        this.dateNaiss = dateNaissance;
    }

    public int getNumero() { return numero; }
    public Date getDateNaissance() { return dateNaiss; }
}