package com.marvin_elsen.eva.uebung_06.aufgabe_06.server;


import java.net.ServerSocket;


public class Server
{
    private static final int NUMBER_OF_SLAVES = 20;


    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException();
        }

        int port = Integer.parseInt(args[0]);

        ServerSocket serverSocket;
        Counter counter = new Counter();
        try
        {
            serverSocket = new ServerSocket(port);

            for (int i = 0; i < NUMBER_OF_SLAVES; i++)
            {
                Thread t = new StaticSlave(serverSocket, counter);
                t.start();
            }
        }
        catch (Exception e)
        {
            System.out.println("Fehler beim Erzeugen des ServerSockets");
        }
    }
}
