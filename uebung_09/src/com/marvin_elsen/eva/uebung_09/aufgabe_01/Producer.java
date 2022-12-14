package com.marvin_elsen.eva.uebung_09.aufgabe_01;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;


public class Producer
{
    public static void main(String[] args) throws IOException, NotBoundException
    {
        RmiMessageQueue queue = (RmiMessageQueue) Naming.lookup("Queue");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF("Test");
        queue.put(byteArrayOutputStream.toByteArray());
    }
}
