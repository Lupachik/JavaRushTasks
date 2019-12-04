package com.javarush.task.task34.task3403;

/*
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recurse(int n) {
        if (n>1){
            boolean flag = true;
            for (int i = 2; i <n+1; i++){
                if(flag & n%i == 0) {
                    System.out.print(i + " ");
                    flag = false;
                    recurse(n/i);
                }
            }
        }

    }

}
