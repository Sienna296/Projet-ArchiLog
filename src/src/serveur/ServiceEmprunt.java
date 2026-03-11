package serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import entities.Abonne;
import entities.Document;
import exceptions.EmpruntException;

public class ServiceEmprunt implements Runnable {
    private Socket socket;

    public ServiceEmprunt(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Scanner in = new Scanner(socket.getInputStream());
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            if (in.hasNextLine()) {
                int numAb = Integer.parseInt(in.nextLine());
                String idDoc = in.nextLine();

                Abonne ab = serveur.ServeurGeneral.trouverAbonne(numAb);
                Document doc = serveur.ServeurGeneral.trouverDoc(idDoc);

                if (ab == null) {
                    out.println("Erreur : Abonné inconnu.");
                } else if (doc == null) {
                    out.println("Erreur : entities.Document inexistant.");
                } else {
                    try {
                        doc.emprunt(ab);
                        out.println("Succès : Vous avez emprunté le document " + idDoc);
                    } catch (EmpruntException e) {
                        out.println("Refus : " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de communication lors d'un emprunt : " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}