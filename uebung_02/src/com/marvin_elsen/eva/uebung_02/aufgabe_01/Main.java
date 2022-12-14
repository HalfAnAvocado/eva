package com.marvin_elsen.eva.uebung_02.aufgabe_01;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Main
{
    public static void main(String[] args)
    {
        Point point = new Point(5, 20);
        Point pointRead;
        final String FILE_NAME = "point.bin";

/*        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
                FILE_NAME))))
        {
            outputStream.writeObject(point);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILE_NAME))))
        {
            pointRead = (Point) inputStream.readObject();

            System.out.println(pointRead);
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
