package mediatheque.serveur;

import Document;
import entities.Abonne;
import java.io.*;
import java.net.*;

public class GestionClient implements Runnable {
    private Socket socket;
    private String type;

    public GestionClient(Socket s, String t) {
        this.socket = s;
        this.type = t;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Lecture des données envoyées par le client
            String idAboStr = in.readLine();
            String idDoc = in.readLine();

            Document d = AppServeur.docs.get(idDoc);

            if (d == null) {
                out.println("Erreur: Document inconnu");
                return;
            }

            if (type.equals("Retour")) {
                d.retour();
            } else {
                Abonne a = AppServeur.abonnes.get(Integer.parseInt(idAboStr));
                if (a == null) {
                    out.println("Erreur: Abonné inconnu");
                    return;
                }

                if (type.equals("Reservation")) d.reservation(a);
                else if (type.equals("Emprunt")) d.emprunt(a);
            }

            out.println("Operation Reussie");
        } catch (Exception e) {
            out.println("Erreur: " + e.getMessage());
        }
    }
}