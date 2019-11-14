package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        try(FileOutputStream outputStream = new FileOutputStream(allFilesContent))
        {
            ArrayList<File> listFiles = new ArrayList<>();
            listF(path.getAbsolutePath(), listFiles);
            Collections.sort(listFiles, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            for (File file: listFiles){
                FileInputStream inputStream = new FileInputStream(file); // создание потока для чтения
                while (inputStream.available()>0){
                    outputStream.write(inputStream.read()); // побайтовое чтение и сразу запись из массива
                }
                outputStream.write(System.lineSeparator().getBytes()); // запись с новой строки
                outputStream.flush(); // ожидание
                inputStream.close(); // закрытие потока для чтения
            }

        }
    }
    //реверсивный метод заполнения списка файлами
    public static void listF(String direct, ArrayList<File> list){
        File fileDir = new File(direct);
        File[] files = fileDir.listFiles();
        for (File file: files){
            if(file.isFile() & file.length()<=50) list.add(file);
            else if(file.isDirectory()) listF(file.getAbsolutePath(), list);
        }
    }
}
