package com.javarush.task.task27.task2712;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        if(tablets.isEmpty()) return;

        // Генерируем случайный заказ каждые ORDER_CREATING_INTERVAL миллисекунд для случайного планшета
        while (!Thread.currentThread().isInterrupted()){
            Tablet tablet = tablets.get((int)(Math.random()*tablets.size())); // выбираем случайный планшет
            tablet.createTestOrder();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {return;}
        }



    }
}
