package io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MultiplicationTable {
    public static void printTable(int num) {
        if (num <= 0) {
            return;
        }
        try (FileOutputStream out = new FileOutputStream("./chapter_002/src/main/resources/res.txt")) {
            StringBuilder firstRow = new StringBuilder();
            for (int col = 1; col <= num; col++) {
                firstRow.append("\t").append(col);
            }
            out.write(firstRow.append("\n").toString().getBytes());
            for (int row = 1; row <= num; row++) {
                StringBuilder rowStr = new StringBuilder(Integer.toString(row));
                for (int col = 1; col <= num; col++) {
                    rowStr.append("\t").append(row * col);
                }
                out.write(rowStr.append("\n").toString().getBytes());
            }
        } catch(IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void main(String[] args) {
        printTable(4);
    }
}
