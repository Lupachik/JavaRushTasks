package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BotClient extends Client {

    public class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if(message!=null && !message.contains(": ")) return;
            String[]strings = message.split(": ");
            String textData = null;
            Calendar calendar = new GregorianCalendar();
            Date date = calendar.getTime();
            switch (strings[1]){
                case "дата":
                    SimpleDateFormat df1 = new SimpleDateFormat("d.MM.YYYY");
                    textData= df1.format(date);
                    break;
                case "день":
                    SimpleDateFormat df2 = new SimpleDateFormat("d");
                    textData = df2.format(date);
                    break;
                case "месяц":
                    SimpleDateFormat df3 = new SimpleDateFormat("MMMM");
                    textData = df3.format(date);
                    break;
                case "год":
                    SimpleDateFormat df4 = new SimpleDateFormat("YYYY");
                    textData = df4.format(date);
                    break;
                case "время":
                    SimpleDateFormat df5 = new SimpleDateFormat("H:mm:ss");
                    textData = df5.format(date);
                    break;
                case "час":
                    SimpleDateFormat df6 = new SimpleDateFormat("H");
                    textData = df6.format(date);
                    break;
                case "минуты":
                    SimpleDateFormat df7 = new SimpleDateFormat("m");
                    textData = df7.format(date);
                    break;

                case "секунды":
                    SimpleDateFormat df8 = new SimpleDateFormat("s");
                    textData = df8.format(date);
                    break;
                default: textData = null;

            }
            if(textData!=null)sendTextMessage("Информация для " + strings[0] + ": " + textData);

        }
    }

    @Override
    protected String getUserName() throws IOException {
        int X = (int)(Math.random()*100);
        return "date_bot_" + X;
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
