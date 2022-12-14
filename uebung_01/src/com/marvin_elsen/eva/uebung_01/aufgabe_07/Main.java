package com.marvin_elsen.eva.uebung_01.aufgabe_07;


import java.io.*;


public class Main
{
    public static void main(String[] args)
    {
        try (DataOutputStream outputStream =
                     new DataOutputStream(new BufferedOutputStream(new FileOutputStream("aufgabe_7_ausgabe"))))
        {
            outputStream.writeBoolean(true);
            outputStream.writeShort((short) 5);
            outputStream.writeInt(5);
            outputStream.writeLong(5);
            outputStream.writeFloat(2.5f);
            outputStream.writeDouble(2.5);
            // 8-bit pro char
            //outputStream.writeBytes("abc");
            // 16-bit pro char
            //outputStream.writeChars("abc");

            // Schreibt die Anzahl der Zeichen vor dem Beginn des Strings
            outputStream.writeUTF("abcd");
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

        // EOFException - if this input stream has reached the end.
        try (DataInputStream inputStream = new DataInputStream(
                new BufferedInputStream(new FileInputStream("aufgabe_7_ausgabe"))))
        {
            System.out.println(inputStream.readBoolean());
            System.out.println(inputStream.readShort());
            System.out.println(inputStream.readInt());
            // Cursor verschiebt sich und fängt die einzelnen Datentypen an der falschen Stelle an zu lesen
            //System.out.println(inputStream.readShort());
            System.out.println(inputStream.readLong());
            System.out.println(inputStream.readFloat());
            System.out.println(inputStream.readDouble());
            System.out.println(inputStream.readUTF());
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

        // Exceptions werden intern bereits verarbeitet
        try (PrintStream outputStream =
                     new PrintStream(new BufferedOutputStream(new FileOutputStream("aufgabe_7_ausgabe_printstream"))))
        {
            outputStream.print(true);
            outputStream.print((short) 5);
            outputStream.print(5);
            outputStream.print((long) 5);
            outputStream.print(2.5f);
            outputStream.print(2.5);
            outputStream.print("abcd");
            outputStream.flush();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not found!");
            System.err.println(e.getMessage());
        }
    }
}
