package io.socket.tutorial;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream())) {
                    String line;
                    while (!(line = in.readLine()).isEmpty()) {
                        System.out.println(line);
                    }
                    out.println("*** Processed ***");
                }
            }
        }
    }
}
