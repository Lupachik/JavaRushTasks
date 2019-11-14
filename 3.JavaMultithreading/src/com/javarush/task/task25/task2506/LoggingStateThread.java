package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
    private Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        State state, laststate = null;
        do{ state = thread.getState();
        if(state!=laststate){
            System.out.println(state);
            laststate = state;
        }

        }while (state != State.TERMINATED);
        interrupt();
    }
}
