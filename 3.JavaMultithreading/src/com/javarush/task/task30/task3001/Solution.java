package com.javarush.task.task30.task3001;

import java.math.BigInteger;

/*
Конвертер систем счислений
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
        //напишите тут ваш код
        // приведем все полученные числа к 10 ному счислению
        // и проверим соответствие числа указанному счислению
        int i = number.getNumberSystem().getNumberSystemIntValue(); // система счисления числа
        String s = number.getDigit(); // число, если число не сооветствует системе счисления то выбросится исключение
        BigInteger a =new BigInteger(s,i); // перевод в десятичную систему, в BigInteger по умолчанию десятичная система
        // перевод из десятичной в нужную
        return new Number(expectedNumberSystem, a.toString(expectedNumberSystem.getNumberSystemIntValue()));
    }
}
