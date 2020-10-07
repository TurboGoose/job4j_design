package io.socket.tutorial;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server running...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            String line;
            String clientNum;
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            System.out.println("Request:");
            line = in.readLine();
            clientNum = line.split(" ")[1].substring(1);
            System.out.println(line);


            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            clientSocket.getOutputStream()));
            String request = String.format("Client #%d is processed" + System.lineSeparator(), clientNum);
            out.write(request);
            out.flush();
            System.out.println("Response: " + request);

            in.close();
            out.close();
            clientSocket.close();
        }
    }

    public static String generateHttpRequest(String str) {
        return String.join(System.lineSeparator(),
                "HTTP/1.0 200 OK",
                "Content-type: text/html",
                "") + str;
    }
}
