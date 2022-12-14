package com.marvin_elsen.eva.uebung_01.aufgabe_01;


import java.io.*;
import java.util.Arrays;


public class Main
{
    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            throw new IllegalArgumentException("CLI-Arguments: FILENAME LENGTH_IN_BYTES");
        }

        String filename = args[0];
        int lengthInBytes = Integer.parseInt(args[1]);

        byte[] bytesToWrite = new byte[lengthInBytes];
        Arrays.fill(bytesToWrite, (byte) 0);

        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filename)))
        {
            outputStream.write(bytesToWrite);
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
