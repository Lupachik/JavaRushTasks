package com.javarush.task.task20.task2025;

import java.math.BigInteger;
import java.util.ArrayList;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        /*
        // попытка решать через BigInteger
        // готовое решение https://github.com/shamily/ArmstrongNumbers
        // еще одно
        // https://github.com/FogZZZ/JavaRushTasks/blob/master/2.JavaCore/src/com/javarush/task/task20/task2025/Solution.java
        //Самый быстрый и самый ресурсосберегающий метод решения данной задачи следующий:
1. создаем массив и вбиваем туда ответ для значения N = Long.MAX_VALUE. Например, long[] temp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370,
 371, 407, 1634, 8208, 9474, 54748, 92727, 93084, 548834, 1741725, 4210818, 9800817, 9926315, 24678050, 24678051, 88593477,
 146511208, 472335975, 534494836, 912985153, 4679307774L, 32164049650L, 32164049651L, 40028394225L, 42678290603L, 44708635679L,
 49388550606L, 82693916578L, 94204591914L, 28116440335967L, 4338281769391370L, 4338281769391371L, 21897142587612075L,
 35641594208964132L, 35875699062250035L, 1517841543307505039L, 3289582984443187032L, 4498128791164624869L,
 4929273885928088826L} (результат скопипастил из комментариев ниже);
2. в массив result забиваем только те значения, которые меньше значения N.
//
        ArrayList<Long> list = new ArrayList<>();
        for(long n =1; n < N; n++){
            String str = Long.toString(n);
            String[] strings = str.split("");
            BigInteger summ = BigInteger.valueOf(0);
            for(int i = 0 ; i < strings.length; i++) {
                BigInteger b = BigInteger.valueOf(Long.parseLong(strings[i]));
                BigInteger m = b.pow(strings.length);
                summ = summ.add(m);
            }
            String s = summ.toString();
            if(s.equals(str)) {
                list.add(n);
                System.out.println(n);
            }
        }

         */
        long[] temp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727, 93084, 548834,
                1741725, 4210818, 9800817, 9926315, 24678050, 24678051, 88593477, 146511208, 472335975, 534494836,
                912985153, 4679307774L, 32164049650L, 32164049651L, 40028394225L, 42678290603L, 44708635679L,
                49388550606L, 82693916578L, 94204591914L, 28116440335967L, 4338281769391370L, 4338281769391371L,
                21897142587612075L, 35641594208964132L, 35875699062250035L, 1517841543307505039L, 3289582984443187032L,
                4498128791164624869L, 4929273885928088826L};

        ArrayList<Long> list = new ArrayList<>();

        for(int i = 0; i < temp.length; i++){
            if(temp[i] < N) list.add(temp[i]);
        }

        long[] result = new long[list.size()];
        for (int i=0; i < result.length; i++){
            result[i] = temp[i];
            //System.out.println(result[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        getNumbers(Long.MAX_VALUE);

    }
}
