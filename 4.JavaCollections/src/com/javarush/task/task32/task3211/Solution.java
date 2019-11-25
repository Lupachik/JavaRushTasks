package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

/* 
Целостность информации
*/
/*
Чтобы определить, были ли зашифрованные данные модифицированы при транспортировке,
отправитель должен рассчитать дайджест сообщения из данных и отправить его вместе с данными.
Другая сторона получая зашифрованные данные и дайджест сообщения,
может пересчитать дайджест сообщения из данных и проверить,
соответствует ли вычисленный дайджест сообщения дайджесту сообщения, полученному с данными.
Если два дайджеста сообщения совпадают, существует вероятность того,
что зашифрованные данные не были изменены во время транспортировки.
 */

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5"); // используем MD5 хэш
        md.update(byteArrayOutputStream.toByteArray());
        byte[] dataBytes = md.digest(); // получаем байт массив для формирования дайджеста

        // формируем строку дайджеста.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataBytes.length; i++) {
            sb.append(Integer.toString((dataBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString().equals(md5);


    }
}
