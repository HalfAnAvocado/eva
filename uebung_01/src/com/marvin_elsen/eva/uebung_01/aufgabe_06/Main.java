package com.marvin_elsen.eva.uebung_01.aufgabe_06;


import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Main
{
    public static void main(String[] args)
    {
        try (MultiplierOutputStream outputStream =
                     new MultiplierOutputStream(new BufferedOutputStream(new FileOutputStream("aufgabe_6_ausgabe")),
                             System.out))
        {
            outputStream.write("Test".getBytes());
            outputStream.flush();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not found!");
            System.err.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.err.println("Anderer Ausgabefehler");
            System.err.println(e.getMessage());
        }
    }
}
