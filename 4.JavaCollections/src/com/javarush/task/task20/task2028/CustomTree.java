package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    private  List<Entry<String>> entryList = new ArrayList<>();

    public CustomTree() {
        this.root = new Entry<>("0");
        entryList.add(this.root);
    }

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }
        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren | availableToAddRightChildren;
        }
        // метод который устанавливает флаги если у родителя есть дети
        void checkChildren() {
            if (leftChild != null) {
                availableToAddLeftChildren = false;
            }
            if (rightChild != null) {
                availableToAddRightChildren = false;
            }
        }
    }

    @Override
    public boolean add(String s) {
        Entry<String> entry = new Entry<>(s); // создаем новый
        // проходим по списку и запускаем для каждого элемента метод checkChildren
        // так мы наудем нужный нам элемент
        for (Entry<String> el: entryList){
            el.checkChildren();
            if (el.isAvailableToAddChildren()){
                entry.parent = el;
                if (el.availableToAddLeftChildren) {
                    el.leftChild = entry;
                    entryList.add(entry);
                    return true;
                }
                else {
                    el.rightChild = entry;
                    entryList.add(entry);
                    return true;
                }
            }
        }

        return false;   //super.add(s);
    }

    @Override
    public int size() {
        return entryList.size()-1;
    }

    public String getParent(String s){
        for (Entry<String> el: entryList){
            if(el.elementName.equals(s)) return el.parent.elementName;
        }
        return null;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
