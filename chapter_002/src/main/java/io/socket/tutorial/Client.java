package io.socket.tutorial;

import java.net.*;
import java.io.*;

public class Client {
    static int numberOfClient = 1;

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8000);
        System.out.println("Client is connecting...");

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        clientSocket.getOutputStream()));
            String request = String.format("Client #%d is trying to connect" + System.lineSeparator(), numberOfClient++);
            out.write(request);
            out.flush();
            System.out.println("Request: " + request);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
            System.out.println("Response:");
            in.lines().forEach(System.out::println);

        out.close();
        in.close();
        clientSocket.close();
        System.out.println("Client disconnected");
    }
}
