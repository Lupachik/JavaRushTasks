package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<>(); // создаем спимок для записи абсолютных путей файла
        Queue<File> queue = new LinkedList<>(); // создаем очередь на основе списка для записи папок
        File fileRoot = new File(root); // создание файла
        if(fileRoot.isDirectory())queue.add(fileRoot);
        else if (fileRoot.isFile())list.add(fileRoot.getAbsolutePath());
        // запускаем цикл, если файл папка записываем в очередь, poll() читаем файл из очереди и удаляет его
        while (!queue.isEmpty()) {
            for (File file : queue.poll().listFiles()){
                if (file.isDirectory())queue.add(file);                  // получаем замену рекурсии
                else if(file.isFile())list.add(file.getAbsolutePath());
            }
        }
        return list;

    }

    public static void main(String[] args) {
        
    }
}
