package com.javarush.task.task30.task3008;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен!");
            while (true){
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendBroadcastMessage(Message message){
        try {
            for (Map.Entry<String,Connection> map: connectionMap.entrySet()){
                map.getValue().send(message);
            }
        } catch (IOException e) {
            System.out.println("Сообщение не отправлено!");
        }
    }

    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection)throws IOException, ClassNotFoundException {

            while (true){
                connection.send(new Message(MessageType.NAME_REQUEST));//отправляем запрос пользователю
                Message messageUser = connection.receive(); // получаем ответ пользователя
                if (messageUser.getType().equals(MessageType.USER_NAME)){ // если получили в ответ имя
                    if(!messageUser.getData().isEmpty()){                        // если имя не пустое
                        if(!connectionMap.containsKey(messageUser.getData())){  // если имени нет в мапе
                            connectionMap.put(messageUser.getData(), connection); // добавляем пользователя и соединение
                            connection.send(new Message(MessageType.NAME_ACCEPTED));// отправляем сообщение о подключении
                            return messageUser.getData();

                        }
                    }
                }
             }

        }

        private void notifyUsers(Connection connection, String userName)throws IOException {
            for (Map.Entry<String, Connection> map: connectionMap.entrySet()){
                String name = map.getKey();
                if (!userName.equals(name)) connection.send(new Message(MessageType.USER_ADDED, name));

            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true) {
                Message messageUser = connection.receive(); // получаем сообщение от пользователя
                if (messageUser.getType() == MessageType.TEXT) {
                    String messageText = userName + ": " + messageUser.getData(); // формируем новый текст
                    Message messageNew = new Message(MessageType.TEXT, messageText); // подготавливаем новое сообщение
                    sendBroadcastMessage(messageNew); // передаем новое сообщение всем пользователям чата
                } else ConsoleHelper.writeMessage("Не правильный формат сообщения, отличный от TEXT!");//System.out.println("Сообщение не является текстом!");
            }
        }

        public void run(){
            String userName = null;
            ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
            try (Connection connection = new Connection(socket)){
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);


            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом!");
            }finally {
                if(userName !=null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
                ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто");
            }

        }
    }
}
