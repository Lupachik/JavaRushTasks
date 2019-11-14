package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        //System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
        //System.out.println(getPartOfString(null));
    }

    public static String getPartOfString(String string) {
        String string1 = null;
        String str= "";
        try {
            String[] strings = string.split(" ");
            for (int i = 1; i < 5; i++){
                str+=strings[i]+" ";
            }
            string1 = str.trim();

        }catch (Exception e){
            throw new TooShortStringException();
        }
        return string1;
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
