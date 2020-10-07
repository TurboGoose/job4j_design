package io.socket;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            System.out.println("Server is running...\n");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream())) {
                    String str = in.readLine();
                    if (str.contains("?msg=Bye")) {
                        break;
                    }
                    do {
                        System.out.println(str);
                    } while (!(str = in.readLine()).isEmpty());
                    System.out.println();
                    out.println("HTTP/1.1 200 OK");
                }
            }
            System.out.println("Server is locked down");
        }
    }
}
