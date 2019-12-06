package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }
    public AmigoSet(Collection<? extends E> collection){
//        Для инициализации поля map воспользуйся конструктором, в который передается Capacity.
//        Вычисли свою Capacity по такой формуле: максимальное из 16 и округленного в большую сторону значения (collection.size()/.75f)
        this.map = new HashMap<>(Math.max(16, (int)(Math.ceil(collection.size()/.75f))));
//        Добавь все элементы из collection в нашу коллекцию.
        this.addAll(collection);
    }

    @Override
    public Iterator iterator() {
        return map.keySet().iterator(); //Получи множество ключей в map, верни его итератор
    }

    @Override
    public int size() {
        return map.size(); //количество ключей в map, равно количеству элементов в map
    }
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        map.remove(o);
        return super.remove(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    //    добавь в map элемент 'e' в качестве ключа и PRESENT в качестве значения.
//    Верни true, если был добавлен новый элемент, иначе верни false.
    @Override
    public boolean add(E e) {

        return map.put(e, PRESENT) == null;
    }

//    Клонируй множество, клонируй map.
//    В случае возникновения исключений выбрось InternalError.
    @Override
    public Object clone() {
        try {
            map.clone();
        }catch (Exception e){
            throw new InternalError();
        }
        return this;
    }
    //метод для сериализации
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        //т.к. в значении мапы стоит заглушка то мы сериализуем ее только по сету ключей
        // нам нужен size set key
        int s = map.size();
        Set<E> keys = map.keySet();
        oos.writeObject(s);
        //записываем set
        for (E e: keys){
            oos.writeObject(e);
        }
        // cappacity and Load Factor get reflecsia
        int capacity = HashMapReflectionHelper.callHiddenMethod(map,"capacity");
        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
        oos.writeObject(capacity);
        oos.writeObject(loadFactor);

    }
    //метод для десирилизации
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        int s = (int)ois.readObject();
        Set<E> keys = new HashSet<>();
        for (int i=0; i < s; i++){
            keys.add((E)ois.readObject());
        }
        int capacity = (int)ois.readObject();
        float loadFactor = (float)ois.readObject();

        map = new HashMap<>(capacity, loadFactor);
        for (E e: keys){
            map.put(e, PRESENT);
        }

    }
}
