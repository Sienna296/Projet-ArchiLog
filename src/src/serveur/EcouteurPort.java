package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EcouteurPort implements Runnable {
    private int port;
    private String typeService;

    public EcouteurPort(int port, String typeService) {
        this.port = port;
        this.typeService = typeService;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) { // [cite: 563]
            System.out.println("Service " + typeService + " prêt sur le port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Attend un client [cite: 564, 573]

                if (typeService.equals("RESERVATION")) {
                    new Thread(new ServiceReservation(clientSocket)).start();
                } else if (typeService.equals("EMPRUNT")) {
                    new Thread(new ServiceEmprunt(clientSocket)).start();
                } else if (typeService.equals("RETOUR")) {
                    new Thread(new ServiceRetour(clientSocket)).start();
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur sur le port " + port + " : " + e.getMessage());
        }
    }
}
