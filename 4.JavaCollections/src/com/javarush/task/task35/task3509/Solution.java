package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < elements.length; i++){
            list.add(elements[i]);
        }
        return list;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        HashSet<T> set = new HashSet<>();
        for (int i = 0; i < elements.length; i++){
            set.add(elements[i]);
        }
        return set;
    }
// Аргументы метода newHashMap должны принимать списки, в которых содержатся наследники типов K и V.
    public static <K ,V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        HashMap<K,V> map = new HashMap<>();
        if(keys.size()!=values.size()) throw new IllegalArgumentException();
        else {
            for (int i=0; i< keys.size(); i++){
                map.put(keys.get(i), values.get(i));
            }
        }
        return map;
    }
}
