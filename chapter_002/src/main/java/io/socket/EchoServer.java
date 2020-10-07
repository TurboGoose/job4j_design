package io.socket;

import java.io.*;
import java.net.*;
import java.util.Objects;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            System.out.println("Server is running...\n");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream())) {
                    String param = parseParameterFromMessage(in.readLine());
                    System.out.println("Client -> " + param);
                    String response = param;
                    if (Objects.equals(param, "Exit")) {
                        break;
                    }
                    else if (Objects.equals(param, "Hello")) {
                        response = "Hello, dear friend";
                    }
                    out.println(generateHttpRequest(response));
                }
            }
            System.out.println("\nServer is locked down");
        }
    }

    //Only for this pattern: "GET /?param=arg HTTP/1.1"
    static String parseParameterFromMessage(String message) {
        String[] requestParts = message.split(" ");
        if (requestParts.length >= 2) {
            String[] paramAndArg = requestParts[1].split("=");
            if (paramAndArg.length == 2) {
                return paramAndArg[1];
            }
        }
        return "";
    }

    static String generateHttpRequest(String str) {
        return "HTTP/1.1 200 OK" + System.lineSeparator().repeat(2) + str;
    }
}
