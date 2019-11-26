package com.javarush.task.task32.task3208;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/* 
RMI-2
*/
public class Solution {
    public static Registry registry;

    // Pretend we're starting an RMI client as the CLIENT_THREAD thread
    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (String bindingName : registry.list()) {
                    Animal service = (Animal) registry.lookup(bindingName);
                    service.printName();
                    service.speak();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    });

    // Pretend we're starting an RMI server as the SERVER_THREAD thread
    public static Thread SERVER_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            //напишите тут ваш код
            try {
                //создание объектов для удаленного доступа
                final Cat service1 = new Cat("Cat");
                final Dog service2 = new Dog("Dog");
                //создание реестра расшареных объетов
                registry = LocateRegistry.createRegistry(2099);
                //создание "заглушки" – приемника удаленных вызовов
                Remote stub1 = UnicastRemoteObject.exportObject(service1,0);
                //регистрация "заглушки" в реесте
                registry.bind("Cat-server", stub1);
                Remote stub2 = UnicastRemoteObject.exportObject(service2,0);
                //регистрация "заглушки" в реесте
                registry.bind("Dog-server", stub2);

            } catch (Exception e) {
                e.printStackTrace(System.err);
            }

        }
    });

    public static void main(String[] args) throws InterruptedException {
        // Starting an RMI server thread
        SERVER_THREAD.start();
        Thread.sleep(1000);
        // Start the client
        CLIENT_THREAD.start();
    }
}