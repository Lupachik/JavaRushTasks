package com.javarush.task.task35.task3507;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
                Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();// создаем список для записи классов удовлетворяющих условию
        List<File> modules = new ArrayList<>();//создаем список файлов, в него запишем нужные нам файлы
        File dir = new File(pathToAnimals);
        //проверяем что путь это директория
        if(dir.isDirectory()){
            for (File file: dir.listFiles()){
                if(file.isFile() && file.getName().endsWith(".class")){
                    modules.add(file);
                }
            }
        }

        /**
         * Загружаем и исполняем каждый модуль.
         */
        for (File module: modules) {
            try {
                //загружаем свой ClassLoader
                MyLoader loader = new MyLoader();

                Class clazz = loader.load(module);
                //Constructor[] constructors = clazz.getConstructors(); // получаем массив конструкторов из файла
                //Class[] interfaces = clazz.getInterfaces();// получаем массив интерфейсов из файла

                // находим конструктор без параметров
                boolean flag = false; // флаг для определения того что конструктор без параметров найден
                for (Constructor constructor: clazz.getConstructors()){
                    if(constructor.getParameterCount() == 0) {
                        flag = true;
                        break;
                    }
                }
                // находим интерфейс, проверяем конструктор, создаем класс и добавляем его в список
                boolean flag1 = false; // флаг для определения того что конструктор без параметров найден
                for (Class cl: clazz.getInterfaces()){
                    if (cl.equals(Animal.class)){
                        flag1 = true;
                        break;
                    }
                }

                if (flag && flag1) {
                    Animal animal = (Animal)clazz.newInstance();
                    set.add(animal);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }


        }


        return set;
    }
}

// создаем свой ClassLoader и переопределяем метод loadClass
// (http://spec-zone.ru/RU/Java/Docs/7/api/java/lang/ClassLoader.html#loadClass(java.lang.String))
// https://www.youtube.com/watch?v=QkJwSUpRGpM

