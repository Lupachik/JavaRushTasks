package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        String str = null;
        try {


            int first = string.indexOf("\t");
            String st = string.substring(first + 1);
            int second = st.indexOf("\t");
            str = st.substring(0, second);
        }catch (Exception e){
            throw new TooShortStringException();
        }
        return str;
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString(null));
    }
}
