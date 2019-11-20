package com.javarush.task.task31.task3106;

import java.util.Map;
import java.util.TreeMap;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("zip.001","a");
        map.put("zip.003","a");
        map.put("zip.004","a");
        map.put("zip.002","a");

        for (Map.Entry<String, String> entry : map.entrySet()){
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

    }
}
