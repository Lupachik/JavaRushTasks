package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    DecimalFormat df = new DecimalFormat("#.00");

    public void printAdvertisementProfit() {
        double total = 0.00;
        TreeMap<Date, Long> treeMap = StatisticManager.getInstance().AdvertisementProfit();
        for (Date date: treeMap.keySet()) { // проходим по дате отсортированную treeMap
            double count = 0.00;
            count = (double) treeMap.get(date)/100;
            ConsoleHelper.writeMessage(sdf.format(date) + " - " + df.format(count).replace(",", ".")); // выводим 2 знака после запятой
            total += count;
        }
        ConsoleHelper.writeMessage("Total - " + df.format(total).replace(",","."));
    }
    public void printCookWorkloading(){
        TreeMap<Date,TreeMap<String, Integer>> treeMap = StatisticManager.getInstance().CookWorkloading();
        for (Map.Entry<Date, TreeMap<String, Integer>> entry: treeMap.entrySet()){
            ConsoleHelper.writeMessage(sdf.format(entry.getKey()));
            for (Map.Entry<String, Integer> entry1: entry.getValue().entrySet()){
                ConsoleHelper.writeMessage(entry1.getKey() + " - " + (int)Math.ceil(((double)entry1.getValue())/60) + " min"); //(int)Math.ceil(((double)orderTime)/60) округление в минутах
            }
            ConsoleHelper.writeMessage("");
        }
    }
    public void printActiveVideoSet(){
        //пример вывода: First Video - 100
        TreeMap<String, Integer> mapHits = StatisticAdvertisementManager.getInstance().AdvertisementManager();
        for(Map.Entry<String, Integer> hit: mapHits.entrySet()){
            if(hit.getValue()>0) ConsoleHelper.writeMessage(hit.getKey() + " - " + hit.getValue());
        }
    }
    public void printArchivedVideoSet(){
        TreeMap<String, Integer> mapHits = StatisticAdvertisementManager.getInstance().AdvertisementManager();
        for(Map.Entry<String, Integer> hit: mapHits.entrySet()){
            if(hit.getValue()==0) ConsoleHelper.writeMessage(hit.getKey());
        }
    }
}
