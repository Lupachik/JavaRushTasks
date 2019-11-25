package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Solution saveObject = new Solution(28);
        // file save
        FileOutputStream fileOutput = new FileOutputStream("/Users/alexey/Desktop/MyProject/1/2.doc");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
        outputStream.writeObject(saveObject);
        fileOutput.close();
        outputStream.close();

        // file load

        FileInputStream fileInput = new FileInputStream("/Users/alexey/Desktop/MyProject/1/2.doc");
        ObjectInputStream inputStream = new ObjectInputStream(fileInput);
        Object object = inputStream.readObject();
        fileInput.close();
        inputStream.close();

        Solution loadObject = (Solution) object;
        System.out.println(loadObject.toString().equals(saveObject.toString()));
        System.out.println(new Solution(4));
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
