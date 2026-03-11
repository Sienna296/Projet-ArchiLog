package serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import entities.Document;
import exceptions.RetourException; // Adapte les imports selon tes packages


public class ServiceRetour implements Runnable {
    private Socket socket;

    public ServiceRetour(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Scanner in = new Scanner(socket.getInputStream());
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            if (in.hasNextLine()) {
                // 1. Lecture de l'unique information nécessaire
                String idDoc = in.nextLine();

                // 2. Recherche de l'objet document
                Document doc = ServeurGeneral.trouverDoc(idDoc);

                // 3. Logique métier
                if (doc == null) {
                    out.println("Erreur : entities.Document inexistant.");
                } else {
                    try {
                        // Tentative de retour
                        doc.retour();
                        out.println("Succès : Le document " + idDoc + " a bien été retourné.");
                    } catch (RetourException e) {
                        out.println("Refus : " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de communication lors d'un retour : " + e.getMessage());
        } finally {
            try {
                socket.close(); // On ferme la connexion [cite: 604]
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}