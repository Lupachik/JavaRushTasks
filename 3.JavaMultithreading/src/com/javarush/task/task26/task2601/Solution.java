package com.javarush.task.task26.task2601;

import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        List<Integer> list = new ArrayList<>();
        for (Integer i : array){
            list.add(i);
        }
        Collections.sort(list);
        Integer median;
        if(list.size()%2 == 0) median = (list.get(list.size()/2) + list.get(list.size()/2-1))/2;
        else median = list.get(list.size()/2);
        List<Integer> mod = new ArrayList<>();
        for(Integer i: list){
            mod.add(Math.abs(i-median));
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (Integer i: mod){
            set.add(i);
        }
        List<Integer> itog = new ArrayList<>();
        for (Integer i: set){
            for (int j = 0; j< mod.size(); j++){
                if(i == mod.get(j)) itog.add(list.get(j));
            }
        }
        itog.toArray(array);

        return array;
    }
}
