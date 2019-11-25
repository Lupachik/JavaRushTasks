package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    Properties prop = new Properties();

    public void fillInPropertiesMap() throws IOException{
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));//считываем файл с консоли
        String fileName = reader.readLine();// получаем имя файла
        reader.close(); // закрываем поток
        FileInputStream is = new FileInputStream(fileName);
        try{
            load(is);
        }catch (Exception e){}


    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        /*
        PrintWriter writer = new PrintWriter(outputStream);
        for(Map.Entry<String, String> pair : properties.entrySet()){
            writer.println(pair.getKey() + " = " + pair.getValue());
        }
        writer.flush();
        writer.close();

         */
        // заполнение Properties файла из Мар
        prop.putAll(properties);
        // сохранение файла
        prop.store(outputStream,"");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (reader.ready()){
            String[] str = reader.readLine().split(" = ");
            properties.put(str[0], str[1]);
        }
        reader.close();

         */
        prop.load(inputStream); // загрузка файла из памяти
        // заполение Map
        for(String name: prop.stringPropertyNames()){
            properties.put(name, prop.getProperty(name));
        }

    }

    public static void main(String[] args) {

    }
}
