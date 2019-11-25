package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        StringWriter writer = new StringWriter();
        if(reader == null) return writer.toString();
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine())!=null){
            byte[] bytes = line.getBytes();
            byte[] bytesNew = new byte[bytes.length];
            for (int i=0; i<bytesNew.length; i++){
                bytesNew[i] = (byte)(bytes[i] + key);
            }
            writer.write(new String(bytesNew));
        }

        return writer.toString();
    }
}
