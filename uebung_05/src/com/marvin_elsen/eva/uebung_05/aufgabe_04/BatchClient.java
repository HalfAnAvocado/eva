package com.marvin_elsen.eva.uebung_05.aufgabe_04;


import java.net.UnknownHostException;


public class BatchClient
{
    public static void main(String[] args) throws UnknownHostException
    {
        if (args.length != 3)
        {
            throw new IllegalArgumentException();
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        int countOfIncrementsToSend = Integer.parseInt(args[2]);

        try (TCPSocket tcpSocket = new TCPSocket(serverAddress, serverPort))
        {
            System.out.println("Zähler zurücksetzen");
            tcpSocket.sendLine("reset");
            String reply = tcpSocket.receiveLine();

            System.out.println("Zähler erhöhen");
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < countOfIncrementsToSend; i++)
            {
                //System.out.println("Sende " + i);
                tcpSocket.sendLine("increment");
            }

            for (int i = 0; i < countOfIncrementsToSend; i++)
            {
                //System.out.println("Lese " + i);
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
