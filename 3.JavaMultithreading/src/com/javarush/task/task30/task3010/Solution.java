package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i<=36; i++){
            try{
                BigInteger a = new BigInteger(args[0],i); // переводим строку с системой исчисления в BigInteger
                list.add(i);
            }catch (Exception e){}

        }


        Collections.sort(list);
        if(list.size()>0) System.out.println(list.get(0));
        else System.out.println("incorrect");
    }
}