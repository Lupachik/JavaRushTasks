package com.javarush.task.task34.task3411;

/* 
Ханойские башни
минимальное количество перемещений 2 в степени N минус 1
*/

public class Solution {
    public static void main(String[] args) {
        int numRings = 3;
        moveRing('A', 'B', 'C', numRings);
    }

    public static void moveRing(char a, char b, char c, int numRings) {
        //напишите тут ваш код
        if(numRings>0){
            moveRing(a, c, b, numRings-1);
            System.out.println(String.format("from %s to %s", a, b));
            moveRing(c, b, a, numRings-1);
        }
        /*
        if(numRings==1)System.out.println(String.format("from %s to %s", a, b));
        else{
            moveRing(a, c, b, numRings-1);
            System.out.println(String.format("from %s to %s", a, b));
            moveRing(c, b, a, numRings-1);
        }
         */
    }
}