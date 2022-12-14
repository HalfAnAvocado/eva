package com.marvin_elsen.eva.uebung_01.aufgabe_05;


import java.io.*;


public class Main
{
    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            throw new IllegalArgumentException("CLI-Arguments: FILENAME");
        }
        String filename = args[0];

        try (PushbackInputStream inputStream =
                     new PushbackInputStream(new BufferedInputStream(new FileInputStream(filename)), 2))
        {
            int byteRead;
            while ((byteRead = inputStream.read()) != -1)
            {
                // ASCII space = dec 32
                // ASCII underscore = dec 95
                if (byteRead == ' ')
                {
                    inputStream.unread('_');
                    inputStream.unread('_');
                    continue;
                }
                System.out.print((char) byteRead);
            }
            System.out.println();
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