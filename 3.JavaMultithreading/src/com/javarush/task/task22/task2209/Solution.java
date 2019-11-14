package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(reader1.readLine()));
        String [] words = null;
        while (reader.ready()){
            words = reader.readLine().replace("\uFEFF", "").split(" ");
        }
        //...
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        // этот метод даёт случайную выборку
        /*ArrayList<String> test = new ArrayList<>();
        Collections.addAll(test,words);
        StringBuilder sb = new StringBuilder("");
        if(words == null || words.length == 0) return new StringBuilder();


        int t = (int)(Math.random()*test.size()-1);
        String tmp = test.get(t);
        sb.append(tmp);
        test.set(t, null);

        for (int i = 0; i<test.size(); i++){

            for (int j = 0; j< test.size(); j++){
                if (test.get(j)!=null){

                    if (tmp.toLowerCase().charAt(tmp.length()-1) == test.get(j).toLowerCase().charAt(0)){
                        sb.append(" "+ test.get(j));
                        tmp = test.get(j);
                        test.set(j, null);
                    }

                }

            }

        }
        for(String s : test){
            if(s !=null) sb.append(" " + s);
        }

         */
        //return sb;
        StringBuilder sb = new StringBuilder("a");

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            list.add(words[i]);
        }
        Collections.sort(list);

        //fin - самая длинная строка на данный момент
        String fin = "";
        //current - текущая строка
        String current = "";
        for (int k = 0; k < list.size(); k++) {

            //клонируем исходный массив и будем работать с ним(каждый раз заново)
            list.add(list.get(0));
            list.remove(0);
            List<String> copy = (ArrayList<String>)list.clone();

            //проходим аглоритм, ставя каждое из возможных слов на первое место

            current = copy.get(0);
            while (copy.size() > 1) {
                boolean findNextFord = false;
                String a = copy.get(0);
                char ch = a.charAt(a.length() - 1);
                String cha = String.valueOf(ch).toLowerCase();
                for (int j = 1; j < copy.size(); j++) {
                    if (String.valueOf(copy.get(j).charAt(0)).toLowerCase().equals(cha)) {
                        current += " " + copy.get(j);
                        copy.set(0, copy.get(j));
                        copy.remove(j);
                        findNextFord = true;
                        break;
                    }
                }
                if(findNextFord == false) break;
            }
            //проверяем, длиннее ли полученная цепочка, чем максимальная на данный момент
            if (current.split(" ").length >= fin.split(" ").length) {
                fin = current;
            }
        }
        return sb = new StringBuilder(fin);

    }
}
