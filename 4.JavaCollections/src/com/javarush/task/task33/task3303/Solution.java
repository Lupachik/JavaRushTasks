package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        // через Path не принял валидатор
        //Path path = Paths.get(fileName); // получаем путь к файлу
        //String jsonString = new String(Files.readAllBytes(path)); // читаем в массив байт и преобразуем в String
        //StringReader reader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(fileName),clazz);
    }

    public static void main(String[] args) {

    }
}
