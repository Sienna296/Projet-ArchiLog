package serveur;

import entities.Document;
import entities.Abonne;
import entities.DVD;
import entities.Livre;

import java.util.*;
import java.time.LocalDate;

public class ServeurGeneral {
    public static List<Abonne> listeAbonnes = new ArrayList<>();
    public static Map<String, Document> mapDocuments = new HashMap<>();

    public static void main(String[] args) {
        // --- INITIALISATION DES DONNÉES ---
        listeAbonnes.add(new Abonne(1, "Geronimo", LocalDate.of(1829, 6, 16)));
        listeAbonnes.add(new Abonne(2, "Sitting Bull", LocalDate.of(1831, 1, 1)));

        mapDocuments.put("L01", new Livre("L01", "Le chant des plaines", 300));
        mapDocuments.put("D01", new DVD("D01", "Danse avec les loups", true)); // DVD Adulte [cite: 23]

        System.out.println("=== SERVEUR MÉDIATHÈQUE DÉMARRÉ ===");

        new Thread(new EcouteurPort(2000, "RESERVATION")).start(); // [cite: 32]
        new Thread(new EcouteurPort(2001, "EMPRUNT")).start();     // [cite: 33]
        new Thread(new EcouteurPort(2002, "RETOUR")).start();      // [cite: 34]
    }

    public static synchronized Abonne trouverAbonne(int num) {
        return listeAbonnes.stream().filter(a -> a.getNumero() == num).findFirst().orElse(null);
    }

    public static synchronized Document trouverDoc(String id) {
        return mapDocuments.get(id);
    }
}

