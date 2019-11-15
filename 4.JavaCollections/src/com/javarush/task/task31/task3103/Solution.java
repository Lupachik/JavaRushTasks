package com.javarush.task.task31.task3103;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/* 
Своя реализация
*/
public class Solution {
    // Метод readBytes должен возвращать все байты файла fileName.
    public static byte[] readBytes(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(fileName));
    }

    // Метод readLines должен возвращать все строки файла fileName с кодировкой по умолчанию.
    public static List<String> readLines(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName),Charset.defaultCharset());
    }

    // Метод writeBytes должен записывать массив bytes в файл fileName.
    public static void writeBytes(String fileName, byte[] bytes) throws IOException {
        Files.write(Paths.get(fileName), bytes);
    }

    //Метод copy должен копировать файл resourceFileName на место destinationFileName.
    public static void copy(String resourceFileName, String destinationFileName) throws IOException {
        Files.copy(Paths.get(resourceFileName), Paths.get(destinationFileName));
    }
}
