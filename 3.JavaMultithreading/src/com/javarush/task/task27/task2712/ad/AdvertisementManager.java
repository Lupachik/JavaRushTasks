package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
    public void processVideos(){
        if (storage.list().isEmpty()) throw new NoVideoAvailableException();
        List<Advertisement> list = new ArrayList<>(); //создаем новый список с видео у который hits > 0
        for (Advertisement  adv: storage.list()){
            if(adv.getHits() > 0) list.add(adv); // проверка hits =0, в конструкторе storage
        }
        // ортируем список по времени от большего к меньшему
        Collections.sort(list, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o2.getDuration() - o1.getDuration();
            }
        });
        int s = list.size()-1; // индекс списка
        long maxSell = getMaxSell(s, timeSeconds, list); // максимальнаяя сумма показов

        List<List<Advertisement>> lists = new ArrayList<>(); // список списков с максимальной суммой показа
        //List<Advertisement> temp = new ArrayList<>(); // временный список для формирования списков

        // цикл перебора заполняющих список
        for (int i = 0; i <= s; i++){
            List<Advertisement> temp = new ArrayList<>(); // временный список для формирования списков
            int timeTemp = list.get(i).getDuration();
            long sellTemp = list.get(i).getAmountPerOneDisplaying();
            temp.add(list.get(i));

            if (timeTemp>timeSeconds) {
                continue;
            }

            if (sellTemp == maxSell && timeTemp <= timeSeconds) listSum(lists, temp);

            for (int j = i+1; j <= s; j++ ){
                timeTemp+=list.get(j).getDuration();
                sellTemp+=list.get(j).getAmountPerOneDisplaying();
                temp.add(list.get(j));

                if (timeTemp>timeSeconds) {
                    temp.remove(temp.size()-1);
                    timeTemp-=list.get(j).getDuration();
                    sellTemp-=list.get(j).getAmountPerOneDisplaying();
                    continue;
                }

                if (sellTemp == maxSell && timeTemp <= timeSeconds) listSum(lists, temp);

            }
        }

        Comparator<List<Advertisement>> listComparatorTime = new Comparator<List<Advertisement>>() {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2) {

                // если время одинаковое то выбираем тот у которого размер меньше, если нет то по максимальному времени
                return (sumTime(o1)==sumTime(o2)) ? o1.size() - o2.size() : sumTime(o2) - sumTime(o1);
            }
        };
        Collections.sort(lists, listComparatorTime); // сортировка списка по времени и размеру
        // получаем вариант с индексом 0, это наш вариант

        List<Advertisement> optimalList = new ArrayList<>(); // можно через copy
        for (Advertisement adv : lists.get(0)){
            optimalList.add(adv);
        }

        // далее мы сортирум в полученном списке ролики по в порядке уменьшения стоимости показа одного
        // рекламного ролика в копейках. Вторичная сортировка - по увеличению стоимости показа одной секунды
        // рекламного ролика в тысячных частях копейки).

        Collections.sort(optimalList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int) (o1.getAmountPerOneDisplaying() == o2.getAmountPerOneDisplaying() ?
                        (o1.getAmountPerOneDisplaying()*1000 / o1.getDuration()) -
                                (o2.getAmountPerOneDisplaying()*1000 / o2.getDuration()):
                        o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
            }
        });

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(optimalList, maxSell, sumTime(optimalList)));
        // событие "видео выбрано" перед отображением рекламы пользователю.  добавил в 14 пункте

        for (Advertisement ad : optimalList){
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", ad.getName(), ad.getAmountPerOneDisplaying(),
                    (int)(ad.getAmountPerOneDisplaying()*1000 / ad.getDuration())));
            ad.revalidate();        
        }




    }
    //пишем новые методы для выбора оптимально показа рекламных роликов
    //для компаратора напишем метод подсчена времени
    private int sumTime(List<Advertisement> list){
        int sum = 0;
        for (Advertisement adv: list){
            sum+=adv.getDuration();
        }
        return sum;
    }

    //метод заполнения списка выбранными списками
    private static void listSum(List<List<Advertisement>> lists, List<Advertisement> temp){
        if (!lists.contains(temp)){
            lists.add(temp);
        }
    }

    //рекурсивный метод выдает максимальную стоимость показов во времени.
    List<Advertisement> listMax = new ArrayList<>();
    // i это индекс списка storage
    private long getMaxSell(int i, int allTime, List<Advertisement> list){
        if(i < 0) return 0;
        if(list.get(i).getDuration()> allTime) {
            return getMaxSell(i-1, allTime, list);
        }
        else {
            return Math.max(getMaxSell(i-1, allTime, list)
                    , getMaxSell(i-1, allTime-list.get(i).getDuration(), list) +
                            list.get(i).getAmountPerOneDisplaying());
        }
    }
}
