package com.javarush.task.task27.task2712.ad;

import java.util.List;
import java.util.TreeMap;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    // метод который показывает вызывает мапу в которых ключ это название видео ролика,
    // а value- количество показов
    public TreeMap<String, Integer> AdvertisementManager(){
        List<Advertisement> list = AdvertisementStorage.getInstance().list(); // список видео роликов
        TreeMap<String, Integer> mapHits = new TreeMap<>(String::compareToIgnoreCase); // treemap с сортировкой не зависимой от регистра
        for (Advertisement adv : list){
            mapHits.put(adv.getName(), adv.getHits()); // заполняем мапу
        }

        return mapHits;
    }
}
