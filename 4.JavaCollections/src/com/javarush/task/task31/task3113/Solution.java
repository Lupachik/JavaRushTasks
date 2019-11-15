package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // считываем с консоли
        String fileName = reader.readLine();
        Path start = Paths.get(fileName);
        if (!Files.isDirectory(start)) System.out.println(String.format("%s - не папка", fileName));
        else {
            List<Path> dirs = new ArrayList<>(); // список директорий
            List<Path> files = new ArrayList<>(); // список файлов
            List<Long> bytes = new ArrayList<>(); // список размера


            Files.walkFileTree(start, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (Files.isDirectory(dir)) dirs.add(dir);
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (Files.isRegularFile(file)){
                        files.add(file);
                        bytes.add(Files.size(file));

                    }
                    return super.visitFile(file, attrs);
                }
            });
            long sum = 0;
            for(Long l: bytes){
                sum +=l;
            }
            System.out.println(String.format("Всего папок - %d", dirs.size()-1));
            System.out.println(String.format("Всего файлов - %d",files.size()));
            System.out.println(String.format("Общий размер - %d", sum));
        }

    }
}
