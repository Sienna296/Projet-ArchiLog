package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class AppClient {
    private final static int PORT = 1234;
    private final static String HOTE = "localhost";
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOTE, PORT);
            System.out.println("Connexion au serveur" + socket.getInetAddress() + ":" + socket.getPort());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner in = new Scanner(System.in);
            Scanner out = new Scanner(socket.getInputStream());


            System.out.println( "Tapez un texte à inverser" );
            System.out.println( "->" );
            String chaine = in.nextLine();
            writer.println(chaine);


            String rep = out.nextLine();
            System.out.println("Votre chaine inversée : " + rep);


            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la connexion", e);
        }
    }
