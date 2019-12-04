package com.javarush.task.task34.task3408;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        if (!cache.containsKey(key)) {
            // применяем рефлексию для поиска конструктора заданного класса
            cache.put(key, clazz.getConstructor(key.getClass()).newInstance(key));
        }

        return cache.get(key);// если ключ существует возвращаем объект
    }

    public boolean put(V obj){
        // с помощью рефлексии получаем ссылку на метод
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true); // разрешаем к нему доступ
            // запускает метод, (K) приводит к типу К
            K key = (K) method.invoke(obj);

            cache.put(key, obj);

            return true;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){}

        //если cache содержит ключ то получаем true
        return false;

    }

    public int size() {
        return cache.size();
    }
}
