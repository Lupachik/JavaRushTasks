package com.javarush.task.task31.task3112;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);
        Path tempPath = Files.createTempFile("url", null, null); // создаем верменный файл
        Files.copy(url.openStream(),tempPath); // Открывает соединение с этим URL и возвращает InputStream для чтения из этого соединения.;

        //resolve(Path other) class Path, Resolve the given path against this path., добавляет путь после этого пути.
        Path path = downloadDirectory.resolve((Paths.get(url.getPath()).getFileName()));


        Files.move(tempPath, path);
        return path;
    }
}
