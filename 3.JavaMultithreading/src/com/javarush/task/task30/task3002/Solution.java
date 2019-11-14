package com.javarush.task.task30.task3002;

import java.math.BigInteger;

/*
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        // определение системы счисления
        int i = 10;
        String n = s;
        if (s.startsWith("0")){
            if(n.startsWith("0x")) {
                n = s.substring(2);
                i = 16;
            }
            else if(n.startsWith("0b")) {
                n = s.substring(2);
                i = 2;
            }
            else i=8;
        }
        int a = Integer.parseInt(n, i);

        return ((Integer)a).toString();
    }
}
