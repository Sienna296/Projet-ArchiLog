package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Abonne implements Serializable {
    private int numero;
    private String nom;
    private Date dateNaiss;
    private boolean banni;

    public Abonne(int numero, String nom, Date dateNaissance, boolean banni) {
        this.numero = numero;
        this.nom = nom;
        this.dateNaiss = dateNaissance;
        this.banni = false;
    }

    public int getNumero() { return numero; }
    public Date getDateNaissance() { return dateNaiss; }

    public boolean isBanni() {
        return banni;
    }

    public boolean aMoinsDe16Ans(){
        //date de mtn
        Calendar cal = Calendar.getInstance();

        //on enleve 16 a auj
        cal.add(Calendar.YEAR, -16);
        Date dateIlYa16Ans = cal.getTime();

        return this.dateNaiss.after(dateIlYa16Ans);
    }
}