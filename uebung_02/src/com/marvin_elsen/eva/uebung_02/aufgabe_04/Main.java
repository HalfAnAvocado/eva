package com.marvin_elsen.eva.uebung_02.aufgabe_04;


import com.marvin_elsen.eva.uebung_02.aufgabe_02.Node;

import java.io.*;


public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Node orgNode = new Node(1, new Node(4, new Node(5, new Node(6))));

        long start = System.currentTimeMillis();

        Node newNode = copyObjectViaFile(orgNode);

        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("Via file took: " + duration + "ms");

        start = System.currentTimeMillis();

        newNode = copyObject(orgNode);

        end = System.currentTimeMillis();
        duration = end - start;

        System.out.println("Via byteArray took: " + duration + "ms");
    }


    private static <T> T copyObjectViaFile(T object) throws IOException, ClassNotFoundException
    {
        T copy;

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
                "copy.bin"))))
        {
            outputStream.writeObject(object);
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream("copy.bin"))))
        {
            copy = (T) inputStream.readObject();
        }

        return copy;
    }


    private static <T> T copyObject(T object) throws IOException, ClassNotFoundException
    {
        T copy;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream)))
        {
            outputStream.writeObject(object);
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new BufferedInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()))))
        {
            copy = (T) inputStream.readObject();
        }

        return copy;
    }
}
