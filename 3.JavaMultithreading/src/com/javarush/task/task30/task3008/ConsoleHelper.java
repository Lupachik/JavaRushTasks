package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public  static void writeMessage(String message){
        System.out.println(message);
    }


    public static String readString () {
        String result = null;
        try {
            result = in.readLine();
        }catch (IOException e){
            System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            return readString();

        }
        return result;
    }
    public static int readInt(){
        int result = 0;
        try {
            result = Integer.parseInt(readString());
        }catch (NumberFormatException e){
            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            return readInt();

        }
        return result;
    }


}
