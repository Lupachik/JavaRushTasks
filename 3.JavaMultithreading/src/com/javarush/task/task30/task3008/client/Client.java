package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread{
        //должен выводить текст message в консоль.
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }
        //должен выводить в консоль информацию о том, что участник с именем userName присоединился к чату.
        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " присоеденился к чату.");
        }
        //должен выводить в консоль, что участник с именем userName покинул чат.
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " покинул чат.");
        }
        /*
        а) Устанавливать значение поля clientConnected внешнего объекта Client в соответствии с переданным параметром.
        б) Оповещать (пробуждать ожидающий) основной поток класса Client.
         */
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this){
                Client.this.notify();
            }
        }

        // Этот метод будет представлять клиента серверу.
        protected void clientHandshake() throws IOException, ClassNotFoundException{
            Message message = null;
            while (true){
                message = connection.receive();
                if(message.getType() == MessageType.NAME_REQUEST){ // сервер запросил имя
                    String userName = getUserName();
                    Message messageNew = new Message(MessageType.USER_NAME, userName);
                    connection.send(messageNew);
                }
                else if(message.getType() == MessageType.NAME_ACCEPTED){ //сервер принял имя
                    notifyConnectionStatusChanged(true);
                    break;
                }
                else throw new IOException("Unexpected MessageType");
            }
        }

        //Этот метод будет реализовывать главный цикл обработки сообщений сервера.
        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            Message message = null;
            while (true){
                message = connection.receive();
                if(message.getType() == MessageType.TEXT) processIncomingMessage(message.getData());
                else if(message.getType() == MessageType.USER_ADDED) informAboutAddingNewUser(message.getData());
                else if(message.getType() == MessageType.USER_REMOVED) informAboutDeletingNewUser(message.getData());
                else throw  new IOException("Unexpected MessageType");

            }
        }

        @Override
        public void run() {
            try{
                String serverAddress = getServerAddress();
                int serverPort = getServerPort();
                Socket socket = new Socket(serverAddress, serverPort);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }catch (IOException | ClassNotFoundException e){
                notifyConnectionStatusChanged(false);
            }
        }
    }

    /*
    должен запросить ввод адреса сервера у пользователя и вернуть введенное значение.
    Адрес может быть строкой, содержащей ip, если клиент и сервер запущен на разных машинах или 'localhost',
    если клиент и сервер работают на одной машине.
     */
    protected String getServerAddress() throws IOException {
        String address = null;
        address = ConsoleHelper.readString();
        return address;
    }

    //должен запрашивать ввод порта сервера и возвращать его.
    protected int getServerPort() throws IOException {
        int serverPort = 0;
        serverPort = ConsoleHelper.readInt();
        return serverPort;
    }
    // должен запрашивать и возвращать имя пользователя.
    protected String getUserName() throws IOException {
        String userName = null;
        userName = ConsoleHelper.readString();
        return userName;
    }
    /*
     данной реализации клиента всегда должен возвращать true (мы всегда отправляем текст введенный в консоль).
     Этот метод может быть переопределен, если мы будем писать какой-нибудь другой клиент, унаследованный от нашего,
     который не должен отправлять введенный в консоль текст.
     */
    protected boolean shouldSendTextFromConsole(){
        return true;
    }
    // должен создавать и возвращать новый объект класса SocketThread
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }
    /*
    создает новое текстовое сообщение, используя переданный текст и отправляет его серверу через соединение connection.
    Если во время отправки произошло исключение IOException,
    то необходимо вывести информацию об этом пользователю и присвоить false полю clientConnected.
     */
    protected void sendTextMessage(String text){
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Сообщение не прошло!");
            clientConnected = false;
        }
    }

    public void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Соединение не установлено!");
                return;
            }
        }
        //notify();
        if(clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено.\n" +
                    "Для выхода наберите команду 'exit'.");
        }else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");

         String text = null;
         while (clientConnected){
             text = ConsoleHelper.readString();
             if(text.equals("exit")) break;
             if(shouldSendTextFromConsole())sendTextMessage(text);
         }




    }

    public static void main(String[] args){
        Client client = new Client();
        client.run();
    }


}

