package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", null);
        map.put("city", "Kiev");
        map.put("age", null);
        System.out.println(map);
        System.out.println(getQuery(map));

    }
    public static String getQuery(Map<String, String> params) {
        String string = "";
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> pair: params.entrySet()){
            if(pair.getValue()!=null) {
                sb.append(pair.getKey());
                sb.append(" = ");
                sb.append("'");
                sb.append(pair.getValue());
                sb.append("'");
                sb.append(" and ");

            }

        }
        String []str = sb.toString().split(" ");
        for (int i=0; i< str.length-1;i++){
            string += str[i] + " ";
        }

        return string.trim();
    }
}
