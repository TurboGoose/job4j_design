package io.iostreams.chat;

import java.io.*;
import java.util.*;

public class Chat {
    private enum State {ACTIVE, INACTIVE, ENDED}
    private State chatState = State.ACTIVE;

    private final String sourceFile;
    private final String logFile;
    private final List<String> phrases = new ArrayList<>();
    private final List<String> log = new ArrayList<>();

    public Chat(String sourceFile, String logFile) {
        this.sourceFile = sourceFile;
        this.logFile = logFile;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            readPhrasesFromSourceFile();
            while (!isEnded()) {
                String message = in.readLine();
                writeToLog(message);
                if (isStopWord(message)) {
                    stopChat();
                }
                else if (isContinueWord(message)) {
                    continueChat();
                }
                else if (isEndWord(message)) {
                    endChat();
                }
                else if (isActive()) {
                    String reply = reply();
                    System.out.println(reply);
                    writeToLog(reply);
                }
            }
            writeLogToFile();
        }
        catch (IOException exc) {
            System.out.println(">>> IO exception:\n\t" + exc.getMessage());
            exc.printStackTrace();
        }
        catch (Exception exc) {
            System.out.println(">>> Unexpected exception:\n\t" + exc.getMessage());
            exc.printStackTrace();
        }
    }

    private void readPhrasesFromSourceFile() throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(sourceFile))) {
            in.lines().forEach(phrases::add);
        }
    }

    private void writeToLog(String line) {
        log.add(line);
    }

    private String reply() {
        return phrases.get(new Random().nextInt(phrases.size()));
    }

    private void writeLogToFile() throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(logFile, true))) {
            log.forEach(out::println);
        }
    }

    private boolean isStopWord(String word) {
        return Objects.equals(word, "stop");
    }

    private boolean isContinueWord(String word) {
        return Objects.equals(word, "continue");
    }

    private boolean isEndWord(String word) {
        return Objects.equals(word, "end");
    }

    private boolean isEnded() {
        return chatState == State.ENDED;
    }

    private boolean isActive() {
        return chatState == State.ACTIVE;
    }

    private void stopChat() {
        chatState = State.INACTIVE;
    }

    private void continueChat() {
        chatState = State.ACTIVE;
    }

    private void endChat() {
        chatState = State.ENDED;
    }
}
