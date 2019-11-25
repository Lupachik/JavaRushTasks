package com.javarush.task.task20.task2027;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }
    // создадим новый класс Pair
    public static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        for (String word: words){
            Word w = new Word(word);
            int in = word.length() - 1;// интервал между первой и последней буквой
            // ищем первую букву слова и последнюю букву слова, находим слово и сравниваем с основным.
            // какое слово совпало те координаты и выбираем.
            // прописываем массив координат из 8 возможных вариантов последней точки слова.
            // int[][] ints = {{i-in,j},{i+in,j},{i,j-in},{i, j+in},{i-in,j+in},{i+in,j+in},{i-in, j-in},{i+in, j-in}};
            // если последняя буква попадает в это массив ее и выбираем
            // создает два ArrayList а, с парами координат первой буквы и последней буквы? последнюю с равниваем с масссивом или списком
            // из 8 возмоных вариантов.
            ArrayList<Pair> start = new ArrayList<>();
            ArrayList<Pair> end = new ArrayList<>();
            for(int y=0; y < crossword.length; y++){
                for (int x = 0; x < crossword[0].length; x++){
                    if (crossword[y][x] == word.charAt(0)) {
                        start.add(new Pair(x, y));

                    }
                }
            }
            for(int y=0; y < crossword.length; y++){
                for (int x = 0; x < crossword[0].length; x++){
                    if (crossword[y][x] == word.charAt(in)) {
                        end.add(new Pair(x, y));

                    }
                }
            }
            // находим нужные пары
            for (Pair pair : start){
                int x = pair.x;
                int y = pair.y;
                //System.out.println(x+", "+ y);
                int[][] ints = {{x-in,y},{x+in,y},{x,y-in},{x, y+in},{x-in,y+in},{x+in,y+in},{x-in, y-in},{x+in, y-in}};
                //System.out.println(Arrays.deepToString(ints));
                for (Pair pair1: end){
                    for (int p=0; p <8; p++){
                        if((ints[p][0]) == pair1.x && (ints[p][1]) == pair1.y) {
                            //System.out.println(pair1.x + ", "+ pair1.y);

                            int vek = p; // вектор направления слова.
                            String s = "";
                            switch (vek){
                                case 0:
                                    for (int i = 0; i < word.length();i++) {
                                        s += (char) crossword[y][x - i];
                                    }
                                    //System.out.println(s);
                                    if(s.equals(word)){
                                        w.setStartPoint(pair.x, pair.y);
                                        w.setEndPoint(pair1.x, pair1.y);
                                        System.out.println(w.toString());
                                        list.add(w);
                                    }
                                    break;
                                case 1:
                                    for (int i = 0; i < word.length();i++) {
                                        s += (char) crossword[y][x + i];
                                    }
                                    //System.out.println(s);
                                    if(s.equals(word)){
                                        w.setStartPoint(pair.x, pair.y);
                                        w.setEndPoint(pair1.x, pair1.y);
                                        System.out.println(w.toString());
                                        list.add(w);
                                    }
                                    break;
                                case 2:
                                    for (int i = 0; i < word.length();i++) {
                                        s += (char) crossword[y-i][x];
                                    }
                                    //System.out.println(s);
                                    if(s.equals(word)){
                                        w.setStartPoint(pair.x, pair.y);
                                        w.setEndPoint(pair1.x, pair1.y);
                                        System.out.println(w.toString());
                                        list.add(w);
                                    }
                                    break;
                                case 3:
                                    for (int i = 0; i < word.length();i++) {
                                        s += (char) crossword[y+i][x];
                                    }
                                    //System.out.println(s);
                                    if(s.equals(word)){
                                        w.setStartPoint(pair.x, pair.y);
                                        w.setEndPoint(pair1.x, pair1.y);
                                        System.out.println(w.toString());
                                        list.add(w);
                                    }
                                    break;
                                case 4:
                                    for (int i = 0; i < word.length();i++) {
                                        s += (char) crossword[y+i][x - i];
                                    }
                                    //System.out.println(s);
                                    if(s.equals(word)){
                                        w.setStartPoint(pair.x, pair.y);
                                        w.setEndPoint(pair1.x, pair1.y);
                                        System.out.println(w.toString());
                                        list.add(w);
                                    }
                                    break;
                                case 5:
                                    for (int i = 0; i < word.length();i++) {
                                        s += (char) crossword[y+i][x + i];
                                    }
                                    //System.out.println(s);
                                    if(s.equals(word)){
                                        w.setStartPoint(pair.x, pair.y);
                                        w.setEndPoint(pair1.x, pair1.y);
                                        System.out.println(w.toString());
                                        list.add(w);
                                    }
                                    break;
                                case 6:
                                    for (int i = 0; i < word.length();i++) {
                                        s += (char) crossword[y-i][x - i];
                                    }
                                    //System.out.println(s);
                                    if(s.equals(word)){
                                        w.setStartPoint(pair.x, pair.y);
                                        w.setEndPoint(pair1.x, pair1.y);
                                        System.out.println(w.toString());
                                        list.add(w);
                                    }
                                    break;
                                case 7:
                                    for (int i = 0; i < word.length();i++) {
                                        s += (char) crossword[y-i][x + i];
                                    }
                                    //System.out.println(s);
                                    if(s.equals(word)){
                                        w.setStartPoint(pair.x, pair.y);
                                        w.setEndPoint(pair1.x, pair1.y);
                                        System.out.println(w.toString());
                                        list.add(w);
                                    }
                                    break;
                            }
                            //w.setStartPoint(pair.x, pair.y);
                            //w.setEndPoint(pair1.x, pair1.y);
                            //System.out.println(w.toString());
                            // дописываем все буквы в слове и проверяем
                            //////////////////////////////
                            ////////////////////////////// код здесь!!!!!!

                            //list.add(w);

                        }


                    }
                }

            }
        }

        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }


}
