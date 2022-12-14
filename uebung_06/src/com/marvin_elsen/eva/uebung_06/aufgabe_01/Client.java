package com.marvin_elsen.eva.uebung_06.aufgabe_01;


import java.net.UnknownHostException;


public class Client
{
    public static void main(String[] args) throws UnknownHostException
    {
        System.out.println("First line of main");
        if (args.length != 3)
        {
            throw new IllegalArgumentException();
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        int countOfIncrementsToSend = Integer.parseInt(args[2]);

        System.out.println("Create new TCPSocketBinary");
        try (TCPSocket tcpSocket = new TCPSocket(serverAddress, serverPort))
        {
            System.out.println("Zähler zurücksetzen");
            tcpSocket.sendLine("reset");
            System.out.println("Warte auf Antwort");
            String reply = tcpSocket.receiveLine();

            long startTime = System.currentTimeMillis();

            System.out.println("Zähler erhöhen");
            for (int i = 0; i < countOfIncrementsToSend; i++)
            {
                tcpSocket.sendLine("increment");
                reply = tcpSocket.receiveLine();
            }

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
            System.out.println("Letzter Zählerstand: " + reply);
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        System.out.println("TCP-Verbindung wurde geschlossen");
    }
}
