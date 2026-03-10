import entities.Abonne;

public interface Document {
    Integer idDoc();
    String titreDoc();

    void reservation(Abonne ab) throws Exception;

    void emprunt(Abonne ab) throws Exception;

    void retour() throws Exception;
}