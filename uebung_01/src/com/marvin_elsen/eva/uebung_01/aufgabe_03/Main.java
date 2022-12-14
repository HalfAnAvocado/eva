package com.marvin_elsen.eva.uebung_01.aufgabe_03;


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

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(filename)))
        {
            int byteCount = 0;
            int byteRead;
            while ((byteRead = inputStream.read()) != -1)
            {
                if (byteCount % 16 == 0)
                {
                    System.out.format("\n%4X\t", byteCount);
                }

                System.out.format("%2X ", byteRead);

                byteCount++;
            }
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
