package com.marvin_elsen.eva.uebung_09.aufgabe_02.server;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class Main
{
    public static void main(String[] args) throws RemoteException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException();
        }

        var port = Integer.parseInt(args[0]);

        var observable = new ConcreteObservable();

        var registry = LocateRegistry.createRegistry(1099);
        registry.rebind("Observable", observable);

        System.out.println("Created registry");

        try (var socket = new DatagramSocket(port))
        {
            while (true)
            {
                byte[] buffer = new byte[Integer.BYTES];
                var datagramPacket = new DatagramPacket(buffer, Integer.BYTES);

                socket.receive(datagramPacket);

                try (var byteArrayInputStream = new ByteArrayInputStream(buffer);
                     var dataInputStream = new DataInputStream(byteArrayInputStream))
                {
                    var sensorValue = dataInputStream.readInt();
                    System.out.println("Received Sensor Value " + sensorValue);

                    observable.notifyObservers(sensorValue);
                }
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
