package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        for (Map.Entry<Long, SoftReference<AnyObject>> map: cacheMap.entrySet()){
            if (map.getKey()==key)return softReference.get();
        }
        return null;

        //напишите тут ваш код
    }

    public AnyObject put(Long key, AnyObject value) {
        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        for (Map.Entry<Long, SoftReference<AnyObject>> map: cacheMap.entrySet()){
            if (map.getKey()==key){
                if(softReference!=null) {
                    AnyObject anyObject = softReference.get(); // создаем жесткую ссылку на объект
                    softReference.clear(); // очищаем мягкую ссылку
                    return anyObject; // выводим ссылку
                }
            }
        }
        return null;

        //напишите тут ваш код
    }

    public AnyObject remove(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.remove(key);

        if(softReference!=null) {
            AnyObject anyObject = softReference.get(); // создаем жесткую ссылку на объект
            softReference.clear(); // очищаем мягкую ссылку
            return anyObject; // выводим ссылку
        }

        return null;

        //напишите тут ваш код
    }
}