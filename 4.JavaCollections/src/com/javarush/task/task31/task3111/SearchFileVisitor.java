package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName; // выражение, встречающееся в названии файла (String);
    private String partOfContent; // выражение, встречающееся в содержимом файла (String);
    private int minSize;
    private int maxSize; // максимальный и минимальный размер файла (int).
    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        // создаем аттрибут поиска по имени файла
        //выражение существует и имя файла содержит его

        boolean foundFlag = true;
        if (partOfName!=null){
            foundFlag = file.getFileName().toString().contains(partOfName); //проверяем по имени
        }
        if (partOfContent!=null && foundFlag){
            foundFlag = new String(content).contains(partOfContent); // проверяем на содержание
        }
        if (maxSize != 0 && foundFlag){
            foundFlag = content.length < maxSize;
        }
        if (minSize !=0 && foundFlag){
            foundFlag = content.length > minSize;
        }
        if (foundFlag)foundFiles.add(file);

        return super.visitFile(file, attrs);
    }
}
