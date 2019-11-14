package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {

    private static final AtomicInteger ai = new AtomicInteger(1);
    {
        setPriority(ai.getAndIncrement());
        if(ai.get()==11)ai.set(1);
    }

    public MyThread() {
    }

    public MyThread(Runnable target) {
        super(target);
        setPriority(ai.getAndIncrement());
        if(ai.get()==11)ai.set(1);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPriority(ai.getAndIncrement());
        if(ai.get()==11)ai.set(1);
    }

    public MyThread(String name) {
        super(name);
        setPriority(ai.getAndIncrement());
        if(ai.get()==11)ai.set(1);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriority(ai.getAndIncrement());
        if(ai.get()==11)ai.set(1);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriority(ai.getAndIncrement());
        if(ai.get()==11)ai.set(1);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriority(ai.getAndIncrement());
        if(ai.get()==11)ai.set(1);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriority(ai.getAndIncrement());
        if(ai.get()==11)ai.set(1);
    }
}
