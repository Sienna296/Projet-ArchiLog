package serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import entities.Document;
import exceptions.RetourException;


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
                String idDoc = in.nextLine();

                Document doc = ServeurGeneral.trouverDoc(idDoc);

                if (doc == null) {
                    out.println("Erreur : entities.Document inexistant.");
                } else {
                    try {
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
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}