package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        String fileName = args[0];
        String zipFileName = args[1];
        File file = new File(fileName); // создаем файл по имени пути

        Map<String, ByteArrayOutputStream> archivedFiles = new HashMap<>(); // создаем мапу для записи файлов архива

        // в try с ресурсами создаем поток для чтения из архива и записи в Map
        try (ZipInputStream zipReader = new ZipInputStream(new FileInputStream(zipFileName))) {
            ZipEntry entry;
            while ((entry = zipReader.getNextEntry()) != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();// поток для записи содержимого файла
                byte[] buffer = new byte[1024]; // или [8*1024]
                int count = 0;
                while ((count = zipReader.read(buffer)) != -1)
                    baos.write(buffer, 0, count);

                archivedFiles.put(entry.getName(), baos);
            }
        }

        // создаем поток для записи файлов в архив
        try (ZipOutputStream zipWriter = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            for (Map.Entry<String, ByteArrayOutputStream> pair : archivedFiles.entrySet()) {
                // если файл существует то пропускаем запись, файл читаем с конца, потому что он может также находится
                // в любой вложенной папке
                if (pair.getKey().substring(pair.getKey().lastIndexOf("/") + 1).equals(file.getName())) continue;
                zipWriter.putNextEntry(new ZipEntry(pair.getKey())); // открываем ZipEntry
                zipWriter.write(pair.getValue().toByteArray());      // и записываем в него файлы с данными из Map
            }

            //записываем новый файл в архив через поток для записи
            ZipEntry zipEntry = new ZipEntry("new/" + file.getName());
            zipWriter.putNextEntry(zipEntry);
            Files.copy(file.toPath(), zipWriter);
        }

   /* ------так не принял, потому что не надо было использовать Path  и Paths------

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(args[1]));
        // проходим по архиву и заполняем мапу
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            if(zipEntry.getName().equals(("new" + File.separator + Paths.get(args[0]).getFileName()))) continue;
            byte[] buffer = new byte[1024*8];
            int len = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = zipInputStream.read(buffer)) > 0) {
                zipInputStream.read(buffer);
                baos.write(buffer, 0, len);
            }
            baos.close();
            zipMap.put(zipEntry.getName(), baos.toByteArray());
        }

        //zipInputStream.closeEntry();
        zipInputStream.close();

        //создадим try c ресурсами для записи
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(args[1]));
            //записываем наш файл
        zipOutputStream.putNextEntry(new ZipEntry(("new" + File.separator + Paths.get(args[0]).getFileName())));
        Files.copy(Paths.get(args[0]), zipOutputStream);
        //zipOutputStream.closeEntry();
        // дописываем из Map
        for (Map.Entry<String, byte[]> entry : zipMap.entrySet()) {
            zipOutputStream.putNextEntry(new ZipEntry(entry.getKey())); // название файла.
            zipOutputStream.write(entry.getValue()); // содержимое файла.
            //zipOutputStream.closeEntry();
        }
        zipOutputStream.close();

    */
    }
}
