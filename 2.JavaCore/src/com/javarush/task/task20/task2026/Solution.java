package com.javarush.task.task20.task2026;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int result = 0;
        //создание нового массива для поиска ключевой точки
        byte[][] b = new byte[a.length+1][a[0].length+1];
        for (int i =0; i < b.length-1; i++){
            for (int j = 0; j < b[0].length-1; j++){
                b[i][j] = a[i][j];
            }
        }
        for (int i =0; i < b.length-1; i++){
            for (int j = 0; j < b[0].length-1; j++){
                if((b[i][j]) == 1 && (b[i][j+1]) == 0 && (b[i+1][j]) == 0 && (b[i+1][j+1]) == 0)result++;
            }
        }
        // ключевая точка. массив для поиска
        //byte[][] c = {{1,0},{0,0}};
        //System.out.println(Arrays.deepToString(b));//вывод на экран нового массива
        return result;
    }
}
