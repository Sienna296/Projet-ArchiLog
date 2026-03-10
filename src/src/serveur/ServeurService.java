package serveur;

import java.net.ServerSocket;

import java.io.IOException;

import java.net.Socket;



class ServeurService extends Thread {
    private int port;
    private String service;

    //demandes de réservation sur le port 2000
    //demandes d’emprunt sur le port 2001
    //demandes de retour sur le port 2002

    public ServeurService(int port, String nomService) {
        this.port = port;
        this.service = nomService;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Service " + service + " démarré sur le port " + port);
            while (true) {
                Socket socket = serverSocket.accept();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

