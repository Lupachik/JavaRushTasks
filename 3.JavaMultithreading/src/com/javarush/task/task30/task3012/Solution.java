package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(2999);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        //1, 3, 9, 27, 81, 243, 729, 2187 -это числовой ряд 3 в степени от 0-7.
        int[] numExp = {1, 3, 9, 27, 81, 243, 729, 2187};
        // преобразуем число в систему счисления 3
        String s = "" + number;
        BigInteger a = new BigInteger(s);
        String str = a.toString(3);// 74 получается как 2202 (2х3^3 + 2x3^2 + 0x3^1 + 2x3^0)
        String [] nums = str.split("");// получае массив символов {2,2,0,2}
        /*
        Схема расчета такая заполняем список числами {1, 0 , -1},
        от минимально порядка к максимальному
        если 2, то записываем -1, и к следующему разряду прибавляем 1, если 1 записываем 1, если 3 записываем 0 и прибавляем 1 к большему разряду
      */
        List<Integer> list = new ArrayList<>();
        int count = 0;
        for(int i=nums.length-1; i >=0; i--){
            int m = 0;
            int c = Integer.parseInt(nums[i]) + count;
            if(c == 1) {
                m = 1;
                count = 0;
            }
            else if (c==2) {
                m = -1;
                count = 1;
            }
            else if (c==3){
                m = 0;
                count = 1;
            }
            list.add(m);
        }
        list.add(count);
        // получили лист {-1,1,-1,0,1}  дальше используя известный массив степени 3, формируем строку.
        String s1 = "";
        for (int i = 0; i < list.size(); i++){
            if(list.get(i) == 0) continue;
            else if(list.get(i)>0) s1 = s1 + " + " + numExp[i];
            else s1 = s1 + " - " + numExp[i];
        }
        String numS = "" + number + " =" + s1;
        System.out.println(numS);
    }
}