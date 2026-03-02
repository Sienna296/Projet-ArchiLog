package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientResa {
    public static void main(String[] args) {
         try (Socket s = new Socket("localhost", 2000);
             PrintWriter out = new PrintWriter(s.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

            Scanner sc = new Scanner(System.in);
            System.out.println("--- Service de Réservation ---");
            System.out.print("Numéro abonné : ");
            out.println(sc.nextLine());
            System.out.print("ID Document : ");
            out.println(sc.nextLine());

            System.out.println("Réponse Serveur : " + in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}