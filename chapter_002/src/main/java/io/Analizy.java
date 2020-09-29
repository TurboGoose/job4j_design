package io;

import java.io.*;
import java.util.*;

public class Analizy {
    private String startTime = null;

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {

            List<TimePeriod> unavailableTimePeriods = new ArrayList<>();
            in.lines().forEach(line -> {
                    String time = parseTime(line);
                    if (hasErrorCodes(line)) {
                        if (startTime == null) {
                            startTime = time;
                        }
                    } else if (startTime != null) {
                        unavailableTimePeriods.add(new TimePeriod(startTime, time));
                        startTime = null;
                    }
            });
            unavailableTimePeriods.forEach(out::println);

        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    private String parseTime(String line) {
        String[] codeTime = line.split(" ");
        if (codeTime.length != 2) {
            return "";
        }
        return codeTime[1];
    }

    private boolean hasErrorCodes(String line) {
        String[] codeTime = line.split(" ");
        if (codeTime.length != 2) {
            return false;
        }
        String code = codeTime[0], time = codeTime[1];
        return Objects.equals(code, "400") || Objects.equals(code, "500");
    }

    public static class TimePeriod {
        public String start;
        public String stop;

        public TimePeriod(String start, String stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return start + " - " + stop;
        }
    }
}
