import entities.Abonne;

public interface Document {
    String idDoc();

    // Exception si déjà réservé ou emprunté
    void reservation(Abonne ab) throws Exception;

    // Exception si réservé pour une autre abonné ou déjà emprunté
    void emprunt(Abonne ab) throws Exception;

    // Sert au retour d'un document ou à l'annulation d'une réservation
    void retour() throws Exception;
}