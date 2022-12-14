package com.marvin_elsen.eva.uebung_09.aufgabe_01;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;


public class Consumer
{
    public static void main(String[] args) throws IOException, NotBoundException
    {
        RmiMessageQueue queue = (RmiMessageQueue) Naming.lookup("Queue");

        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(queue.take()));
        System.out.println(dataInputStream.readUTF());
    }
}
