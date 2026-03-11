package serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import entities.Abonne;
import entities.Document;
import exceptions.ReservationException;

public class ServiceReservation implements Runnable {
    private Socket socket;

    public ServiceReservation(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Scanner in = new Scanner(socket.getInputStream());
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            if (in.hasNextLine()) {
                int numAb = Integer.parseInt(in.nextLine());
                String idDoc = in.nextLine();

                Abonne ab = ServeurGeneral.trouverAbonne(numAb);
                Document doc = ServeurGeneral.trouverDoc(idDoc);

                if (ab == null) {
                    out.println("Erreur : Abonné inconnu.");
                } else if (doc == null) {
                    out.println("Erreur : Document inexistant.");
                } else {
                    try {
                        doc.reservation(ab);
                        out.println("Succès : Le document " + idDoc + " vous est réservé pour une durée de 2h.");
                    } catch (ReservationException e) {
                        out.println("Refus : " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de communication lors d'une réservation : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erreur : le numéro d'abonné reçu n'est pas un nombre valide.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}