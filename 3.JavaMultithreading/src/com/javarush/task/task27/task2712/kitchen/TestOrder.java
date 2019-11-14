package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        dishes = new ArrayList<>();
        Dish[] values = Dish.values();

        int howManyDishes = new Random().nextInt(values.length)+1;
        while (howManyDishes-- > 0) {
            int dishNumber = new Random().nextInt(values.length);
            dishes.add(values[dishNumber]);
        }
        /*dishes.addAll(Arrays.asList(Dish.values())); // добавляем к списку все блюда а потом рандомно удаляем
        int rand = (int)(Math.random()*Dish.values().length) + 1;
        int z = dishes.size() - rand;
        for (int i=0; i<z; i++){
            dishes.remove((int)Math.random()*dishes.size());
        }
  /*      for (Dish dish: Dish.values()){
           int i = (int) Math.random()*2;
           if(i>=1) dishes.add(dish);
        }
        //этот вариант не прошел
   */


    }
}
