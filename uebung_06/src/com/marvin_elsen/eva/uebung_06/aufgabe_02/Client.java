package com.marvin_elsen.eva.uebung_06.aufgabe_02;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class Client
{
    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            throw new IllegalArgumentException();
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        int countOfIncrementsToSend = Integer.parseInt(args[2]);

        try (TCPSocketBinary tcpSocket = new TCPSocketBinary(serverAddress, serverPort))
        {
            System.out.println("Zähler zurücksetzen");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4);
            DataOutputStream dataOutputStream =
                    new DataOutputStream(byteArrayOutputStream);

            dataOutputStream.writeInt(Protocol.RESET.ordinal());
            tcpSocket.send(byteArrayOutputStream.toByteArray());
            byte[] reply = tcpSocket.receive();

            long startTime = System.currentTimeMillis();

            byteArrayOutputStream = new ByteArrayOutputStream(4);
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(Protocol.INCREMENT.ordinal());
            for (int i = 0; i < countOfIncrementsToSend; i++)
            {
                tcpSocket.send(byteArrayOutputStream.toByteArray());
                reply = tcpSocket.receive();
            }
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(reply));
            long stopTime = System.currentTimeMillis();
            long duration = stopTime - startTime;
            System.out.println("Gesamtzeit = " + duration
                    + " msecs");
            if (countOfIncrementsToSend > 0)
            {
                System.out.println("Durchschnittszeit = "
                        + ((duration) / (float) countOfIncrementsToSend)
                        + " msecs");
            }
            System.out.println("Letzter Zählerstand: " + dataInputStream.readInt());
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        System.out.println("TCP-Verbindung wurde geschlossen");
    }
}
