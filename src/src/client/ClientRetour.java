package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientRetour {
    private final static int PORT = 2002;
    private final static String HOTE = "localhost";

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOTE, PORT);
             Scanner console = new Scanner(System.in);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner in = new Scanner(socket.getInputStream())) {

            System.out.println("--- BORNE DE RETOUR ---");

            System.out.print("Identifiant du document à rendre : ");
            String idDoc = console.nextLine();


            out.println(idDoc);

            if (in.hasNextLine()) {
                System.out.println("Réponse serveur : " + in.nextLine());
            }

        } catch (IOException e) {
            System.err.println("Erreur de connexion au port 2002");
        }
    }
}

