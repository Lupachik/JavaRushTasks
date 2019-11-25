package com.javarush.task.task32.task3210;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];// путь к файлу
        String number = args[1];// число, позиция в файле
        String text = args[2]; // текст

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw"); // файл открыт и для чтения и для записи
        long fileLength = raf.length(); // получаем длину файла
        long numberFile = Long.parseLong(number); // номер символа, позиция с которой вести запись
        long textLength = text.length(); // длина текста
        // byte[] bytes = Files.readAllBytes(Paths.get(fileName));//  не принял валидатор
        byte[] bytes = text.getBytes();// создаем массив байт для записи из полученного файла, равный длине нужного текста
        // Считать текст с файла начиная с позиции number, длинной такой же как и длинна переданного текста в третьем параметре.
        // считывать данные с файла при помощи метода read(byte[] b, int off, int len).
        raf.seek(numberFile);
        raf.read(bytes, 0, (int) textLength);// записываем в массив bytes нужный нам текст.

        // Если считанный текст такой же как и text, то программа должна записать в конец переданного файла строку 'true'.
        // Если считанный текст НЕ такой же как и text, то программа должна записать в конец переданного файла строку 'false'.
        if(new String(bytes).equals(text)){
            raf.seek(fileLength);
            raf.write("true".getBytes());
        }
        else {
            raf.seek(fileLength);
            raf.write("false".getBytes());
        }


    }
}
