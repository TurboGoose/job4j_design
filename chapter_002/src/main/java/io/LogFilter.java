package io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List <String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            result = in.lines().
                    filter(line -> {
                        String[] data = line.split(" ");
                        return Objects.equals(data[data.length - 2], "404");
                    }).collect(Collectors.toList());
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(str -> out.write(str + "\n"));
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void main(String[] args) {
        final String PATH = "./files/read/log.txt";
        List<String> log = filter(PATH);
        save(log, "./files/write/404.txt");
        log.forEach(System.out::println);
    }
}