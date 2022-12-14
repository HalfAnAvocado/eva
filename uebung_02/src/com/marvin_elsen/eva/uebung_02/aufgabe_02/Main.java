package com.marvin_elsen.eva.uebung_02.aufgabe_02;


import java.io.*;


public class Main
{
    public static void main(String[] args)
    {
        Node b = new Node(2);
        Node a = new Node(1, b);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream)))
        {
            outputStream.writeObject(a);
            b.setId(3);
            outputStream.writeObject(b);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new BufferedInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()))))
        {
            a = (Node) inputStream.readObject();
            b = (Node) inputStream.readObject();

            System.out.println("a.b == b: " + (a.getNextNode() == b));
            System.out.println("a.b.id = " + a.getNextNode().getId());
            System.out.println("b.id = " + b.getId());
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
