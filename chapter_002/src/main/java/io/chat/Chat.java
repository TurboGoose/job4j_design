package io.chat;

import java.io.*;
import java.util.*;

public class Chat {
    private enum State {ACTIVE, INACTIVE, ENDED}

    private final int BUFFER_SIZE = 10;
    private final Queue<String> replyBuffer = new ArrayDeque<>(BUFFER_SIZE);
    private final List<String> logBuffer = new ArrayList<>(BUFFER_SIZE);

    private final String sourceFile;
    private final String logFile;
    private State chatState = State.ACTIVE;

    public Chat(String sourceFile, String logFile) {
        this.sourceFile = sourceFile;
        this.logFile = logFile;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (!isEnded()) {
                String message = in.readLine();

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
                writeToLog(message);
            }
        }
        catch (IOException exc) {
            System.out.println(">>> IO exception:\n\t" + exc.getMessage());
        }
        catch (Exception exc) {
            System.out.println(">>> Unexpected exception:\n\t" + exc.getMessage());
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

    String reply() throws IOException {
        if (replyBuffer.isEmpty()) {
            updateReplyBuffer();
        }
        return replyBuffer.poll();
    }

    private void updateReplyBuffer() throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(sourceFile))) {
            int totalNumberOfLines = (int) in.lines().count();
            int[] chosenNumbersOfLines = new Random().ints(BUFFER_SIZE, 0, totalNumberOfLines)
                    .distinct().sorted().toArray();
            int curNum = 0;
            String curLine = "No phrase";
            for (int num : chosenNumbersOfLines) {
                while (curNum++ != num) {
                    if ((curLine = in.readLine()) == null) {
                        return;
                    }
                }
                replyBuffer.offer(curLine);
            }
        }
    }

    void writeToLog(String line) throws IOException {
        if (logBuffer.size() >= BUFFER_SIZE) {
            updateLogBuffer();
        }
        logBuffer.add(line);
    }

    private void updateLogBuffer() throws IOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(logFile))) {
            for (String log : logBuffer) {
                out.append(log);
            }
        }
        logBuffer.clear();
    }
}
