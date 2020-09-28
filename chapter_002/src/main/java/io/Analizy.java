package io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analizy {
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

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            List<String> logs = in.lines().collect(Collectors.toList());
            defineUnavailableTimePeriods(logs).forEach(out::println);
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    List<TimePeriod> defineUnavailableTimePeriods(List<String> logs) {
        List<TimePeriod> result = new ArrayList<>();
        String start = null;
        for (String log : logs) {
            String[] codeTime = log.split(" ");
            String code = codeTime[0], time = codeTime[1];
            if (Objects.equals(code, "400") || Objects.equals(code, "500")) {
                if (start == null) {
                    start = time;
                }
            } else {
                if (start != null) {
                    result.add(new TimePeriod(start, time));
                }
                start = null;
            }
        }
        return result;
    }
}
