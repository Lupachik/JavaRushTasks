package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    protected void initDishes() throws IOException {
        //ConsoleHelper.writeMessage(Dish.allDishesToString()); //?
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    @Override
    public String toString() {
        return dishes.isEmpty()?"" :String.format("Your order: %s of %s",dishes, tablet);
    }

    public int getTotalCookingTime(){
        int cookingTime = 0;
        for (Dish dish: dishes){
            cookingTime+=dish.getDuration();
        }
        return cookingTime;
    }
    public boolean isEmpty(){
        if(!dishes.isEmpty())return false;
        return true;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }


}
