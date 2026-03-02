package mediatheque.serveur;

import serveur.GestionClient;
import entities.Abonne;
import entities.Livre;
import entities.DVD;

import javax.swing.text.Document;
import java.net.*;
import java.util.*;

public class AppServeur {
    public static Map<String, Document> docs = new HashMap<>();
    public static Map<Integer, Abonne> abonnes = new HashMap<>();

    public static void main(String[] args) throws Exception {
        docs.put("L1", new Livre("L1", "Le dernier des Mohicans"));
        docs.put("D1", new DVD("D1", "Geronimo", true));

        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 1);
        abonnes.put(101, new Abonne(101, "Sitting Bull", cal.getTime()));

        new Thread(() -> ecouter(2000, "Reservation")).start();
        new Thread(() -> ecouter(2001, "Emprunt")).start();
        new Thread(() -> ecouter(2002, "Retour")).start();

        System.out.println("Serveur Médiathèque lancé sur les ports 2000, 2001, 2002...");
    }

    private static void ecouter(int port, String type) {
        try (ServerSocket ss = new ServerSocket(port)) {
            while (true) {
                Socket s = ss.accept();
                new Thread(new GestionClient(s, type)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}