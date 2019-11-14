package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/* 
Палиндром?
это число в прямом значении и реверсном одинаковое
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }
    private static Set<Integer> getRadix(String number){
        Set<Integer> set = new HashSet<>();
        // перебор систем счисления
        for (int i=2; i<=36; i++){
            try {
                BigInteger a = new BigInteger(number); //переводим число в BigInteger,
                String sR = a.toString(i);// чтобы применить метод перевода в новую систему счисления

                String [] str = sR.split(""); // получаем массив символов
                List<String> list = Arrays.asList(str); //получаем список на основе массива
                Collections.reverse(list); // делаем ревер списка
                String rS = "";
                for (String st : list){
                    rS+=st;
                }
                if (rS.equals(sR)) set.add(i);

            }catch (NumberFormatException e){} // ловим исключение если число не подходит для системы счисления

            //обрабатываем полученный String на Палиндром

        }

        return set;
    }
}