package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public static void main(String[] args) {
        final String PATH = "./files/read/log.txt";
        List<String> log = filter(PATH);
        log.forEach(System.out::println);
    }
}