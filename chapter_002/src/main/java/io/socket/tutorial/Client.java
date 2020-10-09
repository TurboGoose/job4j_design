package io.socket.tutorial;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Client is connecting...");
        try (Socket clientSocket = new Socket("127.0.0.1", 8000);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String str;
            while (!(str = in.readLine()).isEmpty()) {
                System.out.println(str);
            }
            out.println("Client is connecting...");
        }
    }
}
