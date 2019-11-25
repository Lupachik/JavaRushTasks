package com.javarush.task.task32.task3204;

import java.io.*;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        InputStream inputStream = new ByteArrayInputStream(passWord().getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        try {
            while (bis.available() > 0){
                int data = bis.read();
                outputStream.write(data);
            }

        }catch (IOException e){}

        return outputStream;
    }

    public static String passWord(){
        String base = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        String str1 = "0123456789";
        String str2 = "qwertyuiopasdfghjklzxcvbnm";
        String str3 = "QWERTYUIOPASDFGHJKLZXCVBNM";
        // флаги для выполнения условий
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        char[]chars = base.toCharArray();
        char[]passChar = new char[8];
        // заполняем массив
        for(int i = 0; i < 8; i++){
            int z = (int)(Math.random()*61); // случайное число из массива char
            passChar[i] = chars[z];
        }
        // проверка массива на соответствие условий
        for(int i = 0; i < passChar.length; i++){
            if(str1.contains(Character.toString(passChar[i]))) flag1 = true;
            else if(str2.contains(Character.toString(passChar[i]))) flag2 = true;
            else if(str3.contains(Character.toString(passChar[i]))) flag3 = true;
        }
        if(flag1 && flag2 && flag3) {
            return String.valueOf(passChar);
        }
        return passWord();
    }
}