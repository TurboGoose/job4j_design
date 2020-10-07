package io.iostreams.chat;

public class ChatApp {
    public static void main(String[] args){
        if (args.length != 2) {
            return;
        }
        String sourceFile = args[0];
        String logFile = args[1];
        new Chat(sourceFile, logFile).run();
    }
}
