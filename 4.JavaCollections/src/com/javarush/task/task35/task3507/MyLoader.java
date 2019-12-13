package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MyLoader extends ClassLoader {
    //создаем свой метод для classloader
    public Class load(File file){
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            return defineClass(null, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
