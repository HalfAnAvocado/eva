package com.marvin_elsen.eva.uebung_01.aufgabe_04;


import java.io.*;


public class Main
{
    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            throw new IllegalArgumentException("CLI Arguments: FILE_TO_COPY FILE_TO_COPY_TO");
        }

        String fileToCopy = args[0];
        String fileToPaste = args[1];

        long start = System.currentTimeMillis();
        writeWithoutBuffer(fileToCopy, fileToPaste);
        long duration = System.currentTimeMillis() - start;
        System.out.println("Without Buffer: " + duration + "ms");

        start = System.currentTimeMillis();
        writeWithBuffer(fileToCopy, fileToPaste);
        duration = System.currentTimeMillis() - start;
        System.out.println("With Buffer: " + duration + "ms");
    }


    private static void writeWithoutBuffer(String fileToCopy, String fileToPaste)
    {
        try (InputStream inputStream = new FileInputStream(fileToCopy);
             OutputStream outputStream = new FileOutputStream(fileToPaste))
        {
            int byteRead;
            while ((byteRead = inputStream.read()) != -1)
                outputStream.write(byteRead);

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


    private static void writeWithBuffer(String fileToCopy, String fileToPaste)
    {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(fileToCopy));
             OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileToPaste)))
        {
            int byteRead;
            while ((byteRead = inputStream.read()) != -1)
                outputStream.write(byteRead);

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
