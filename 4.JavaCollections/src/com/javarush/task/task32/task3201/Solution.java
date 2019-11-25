package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];// путь к файлу
        String number = args[1];// число, позиция в файле
        String text = args[2]; // текст

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw"); // файл открыт и для чтения и для записи
        long fileLength = raf.length(); // получаем длину файла
        long numberFile = Long.parseLong(number); // номер символа, позиция с кторой вести запись
        long textLength = text.length(); // длина текста

        // далее мы сравниваем длину файла и длину текста начиная с numberFile позиции
        // если текст помещается то записываем, если больше то записываем в конец.
        if(fileLength >= (textLength + numberFile-1)){
            raf.seek(numberFile); // ставим курсор в нужную позицию
            raf.write(text.getBytes()); // записываем текст
        }
        else {
            raf.seek(raf.length()); // ставим курсор в конец файла
            raf.write(text.getBytes()); // записываем текст
        }

    }
}
