package entities;

public class DVD extends Livre {
    private boolean adulte;

    public DVD(String id, String titre, boolean adulte) {
        super(id, titre);
        this.adulte = adulte;
    }

    @Override
    public synchronized void emprunt(Abonne ab) throws Exception {
        if (adulte) {
            long ageMilli = System.currentTimeMillis() - ab.getDateNaissance().getTime();
            if (ageMilli < 504911232000L) {
                throw new Exception("vous n'avez pas l'âge pour emprunter ce DVD");
            }
        }
        super.emprunt(ab);
    }
}