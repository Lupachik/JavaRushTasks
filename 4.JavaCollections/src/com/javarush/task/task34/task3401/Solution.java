package com.javarush.task.task34.task3401;

/* 
Числа Фибоначчи с помощью рекурсии
1 =1
2 =1
3 = 1+1
4 = 2+1
5 = 3+2
6 = 5+3
и т.д.
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.fibonacci(9));     //34
        System.out.println(solution.fibonacci(5));     //5
        System.out.println(solution.fibonacci(2));     //1
        System.out.println(solution.fibonacci(1));     //1
    }

    public int fibonacci(int n) {
        int fib = 0;
        if(n==2 || n==1) return 1;
        fib = fibonacci(n-1)+fibonacci(n-2);
        return fib;
    }
}
