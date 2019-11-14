package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static class CustomizedComparator<T> implements Comparator<T>{
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>...comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T o1, T o2) {
            for(Comparator c: comparators){
                int comp = c.compare(o1,o2);
                if(comp!=0) return comp;
            }
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
