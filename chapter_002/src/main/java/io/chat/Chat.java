package io.chat;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chat {
    private enum State {ACTIVE, INACTIVE, ENDED}
    private State chatState = State.ACTIVE;

    private final int BUFFER_SIZE = 10;
    private final Queue<String> replyBuffer = new ArrayDeque<>(BUFFER_SIZE);
    private final List<String> logBuffer = new ArrayList<>(BUFFER_SIZE);

    private final String sourceFile;
    private int numberOfSourceFileLines;
    private final String logFile;

    public Chat(String sourceFile, String logFile) {
        this.sourceFile = sourceFile;
        this.logFile = logFile;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            readNumberOfSourceFileLines();
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

    private void readNumberOfSourceFileLines() throws IOException {
        try (BufferedReader source = new BufferedReader(new FileReader(sourceFile))) {
            numberOfSourceFileLines = (int) source.lines().count();
        }
    }

    private void writeToLog(String line) throws IOException {
        if (logBuffer.size() >= BUFFER_SIZE) {
            updateLogBuffer();
        }
        logBuffer.add(line);
    }

    private void updateLogBuffer() throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(logFile, true))) {
            for (String log : logBuffer) {
                out.println(log);
            }
        }
        logBuffer.clear();
    }

    private String reply() throws IOException {
        if (replyBuffer.isEmpty()) {
            updateReplyBuffer();
        }
        return replyBuffer.poll();
    }

    private void updateReplyBuffer() throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(sourceFile))) {
            List<Integer> chosenNumbers = generateNRandomUniqueNumbersInRange(0, numberOfSourceFileLines, BUFFER_SIZE);
            List<String> chosenLines = new ArrayList<>();
            String line;
            for (int i = 0; i < Collections.max(chosenNumbers); i++) {
                if ((line = in.readLine()) == null) {
                    break;
                }
                if (chosenNumbers.contains(i)) {
                    chosenLines.add(line);
                }
            }
            Collections.shuffle(chosenLines);
            replyBuffer.addAll(chosenLines);
        }
    }

    private List<Integer> generateNRandomUniqueNumbersInRange(int from, int to, int n) {
        List<Integer> result = from > to ?
                new ArrayList<>() :
                IntStream.rangeClosed(from, to - 1).boxed().collect(Collectors.toList());
        Collections.shuffle(result);
        return result.subList(0, Math.min(n, result.size()));
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

    private void endChat() throws IOException {
        chatState = State.ENDED;
        updateLogBuffer();
    }
}
