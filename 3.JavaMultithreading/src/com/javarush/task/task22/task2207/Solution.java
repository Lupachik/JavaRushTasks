package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str  = reader.readLine();
        FileInputStream fis= new FileInputStream(str);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        ArrayList<String> list = new ArrayList<>();
        while (br.ready()){
            String string = br.readLine();
            // нужно удалить перед первым словом маркер последовательности байтов
            String []strings = string.replace("\uFEFF","").split(" ");
            for (int i=0; i< strings.length; i++){
                list.add(strings[i]);
            }
        }
        fis.close();
        for (String s:list){
            int a = s.length();
            System.out.println(s + " " + a);
        }


        for(int i=0;i<list.size();i++){
            StringBuilder sb = new StringBuilder(list.get(i));
            String st = sb.reverse().toString();
            for (int j=i+1; j<list.size();j++){
                if(list.get(j).equals(st)){
                    Pair pair = new Pair(list.get(i),list.get(j));
                    result.add(pair);

                    System.out.println(list.get(i) + " " + list.get(j));
                    list.remove(j);
                }
            }
        }

        for (String s:list){
            System.out.println(s);
        }
        for (Pair pair:result){
            System.out.println(pair.first +" "+pair.second);
        }




    }

    public static class Pair {
        public Pair() {}

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }



        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                        first == null ? second :
                            second == null ? first :
                                first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }

    }

}
