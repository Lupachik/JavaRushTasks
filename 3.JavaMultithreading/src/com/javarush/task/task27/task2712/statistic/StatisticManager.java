package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {
    }

   public void register(EventDataRow data){
        statisticStorage.put(data);
   }

   private class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

       public StatisticStorage() {
           for (EventType eventType : EventType.values()){
               storage.put(eventType, new ArrayList<EventDataRow>());
           }
       }

       private void put(EventDataRow data){
           storage.get(data.getType()).add(data);    // добавление даты для каждого полученного события по ключу.
       }

       // Дополнительно добавь вспомогательный метод get в класс хранилища, чтобы получить доступ к данным.

       public Map<EventType, List<EventDataRow>> getStorage() {
           return storage;
       }
   }


    //метод, который из хранилища достанет все данные, относящиеся к отображению рекламы, и посчитает общую прибыль за каждый день.
    public TreeMap<Date, Long> AdvertisementProfit(){
       List<EventDataRow> listsVideoEvent  = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS); // список событий по показу рекламы.
        Set<Date> dateSet = new HashSet<>(); // создаем Set для формирования списка дат
        SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH); //для преобразования даты, чтобы по ней фильтровать суммы за день
        // проходим по списку событий чтобы получить даты
        for (EventDataRow edr :listsVideoEvent){
            dateSet.add(getParse(sdf, edr)); // этим способом мы убираем минуты и оставляем только дату
        }
        //создаем TreeMap где key - дата, а value - сумма стоимости всех показов рекламы в этот день.
        TreeMap<Date, Long> mapVideo = new TreeMap<>(Comparator.reverseOrder()); // сортируем по убыванию
        for (Date date: dateSet){
            long amountSum = 0;
            for (EventDataRow edr :listsVideoEvent){
                VideoSelectedEventDataRow videoSelectedEventDataRow = (VideoSelectedEventDataRow) edr; // приводим событие к видео
                if(getParse(sdf, edr).equals(date)) amountSum+=videoSelectedEventDataRow.getAmount();
            }
            mapVideo.put(date,amountSum);

        }
        return mapVideo;
    }

    // вынесли отдельным методом формат даты, только день месяц и год
    private Date getParse(SimpleDateFormat sdf, EventDataRow edr) {
       Date date = null;
        try {
            date = sdf.parse(sdf.format(edr.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // метод, который из хранилища достанет все данные, относящиеся к работе повара,
    // и посчитает общую продолжительность работы для каждого повара отдельно.
    public TreeMap<Date,TreeMap<String, Integer>> CookWorkloading(){
        List<EventDataRow> listsCookEvent  = statisticStorage.getStorage().get(EventType.COOKED_ORDER); // список событий по работе поваров
        Set<Date> dateSet = new HashSet<>(); // создаем Set для формирования списка дат
        Set<String> cooks = new HashSet<>(); // создаем Set из имен поваров.
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH); //для преобразования даты, чтобы по ней фильтровать суммы за день
        // проходим по списку событий чтобы получить даты
        for (EventDataRow edr :listsCookEvent){
            dateSet.add(getParse(sdf, edr)); // этим способом мы убираем минуты и оставляем только дату
            cooks.add(((CookedOrderEventDataRow)edr).getCookName()); // заполняем список именами поваров
        }
        //создаем TreeMap где key - дата, а value - сумма стоимости всех показов рекламы в этот день.
        TreeMap<Date,TreeMap<String, Integer>> mapCooks = new TreeMap<>(Comparator.reverseOrder()); // сортируем по убыванию
        for (Date date: dateSet){
            ArrayList<CookedOrderEventDataRow> listTemp = new ArrayList<>();// список событий order для каждого дня

            TreeMap<String,Integer> mapCookTime = new TreeMap<>(); //список поваров и времени рааботы каждого в этот день

            for (EventDataRow edr :listsCookEvent) {
                if (getParse(sdf, edr).equals(date)) {               // формирум для каждой даты список событий и заполняем его.
                    CookedOrderEventDataRow cookedOrderEventDataRow = (CookedOrderEventDataRow) edr; // приводим событие к заказу повару
                    listTemp.add(cookedOrderEventDataRow);
                }
            }
            // проходим по полученному списку собый списком поваров и получаем итоговое время для каждого повара


            for (String cook : cooks) {
                int orderTime = 0;
                for (CookedOrderEventDataRow event : listTemp) {
                    if (cook.equals(event.getCookName()))
                        orderTime += event.getTime();
                }

                mapCookTime.put(cook, orderTime);
            }
            mapCooks.put(date,mapCookTime);
        }

       return mapCooks;
    }
}
