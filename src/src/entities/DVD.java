package entities;

public abstract class DVD extends Livre {
    private boolean adulte;
    private String titreDVD;

    public DVD(String titre, boolean adulte) {
        super();
        this.adulte = adulte;
    }

    @Override
    public synchronized void emprunt(Abonne ab) throws EmpruntException {
        if (adulte) {
            long ageMilli = System.currentTimeMillis() - ab.getDateNaissance().getTime();
            if (ageMilli < 504911232000L) {
                throw new Exception("vous n'avez pas l'âge pour emprunter ce DVD");
            }
        }
        super.emprunt(ab);
    }
}