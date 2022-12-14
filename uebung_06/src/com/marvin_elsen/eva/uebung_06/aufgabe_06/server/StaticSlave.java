package com.marvin_elsen.eva.uebung_06.aufgabe_06.server;


import com.marvin_elsen.eva.uebung_06.aufgabe_01.TCPSocket;

import java.net.ServerSocket;


public class StaticSlave extends Thread
{
    private final ServerSocket serverSocket;
    private final Counter counter;


    public StaticSlave(ServerSocket serverSocket, Counter counter)
    {
        this.serverSocket = serverSocket;
        this.counter = counter;
    }


    @Override
    public void run()
    {
        while (true)
        {
            try (TCPSocket tcpSocket = new TCPSocket(serverSocket.accept()))
            {
                while (true)
                {
                    String request = tcpSocket.receiveLine();

                    int result;
                    if (request != null)
                    {
                        if (request.equals("increment"))
                        {
                            result = counter.increment();
                        }
                        else if (request.equals("reset"))
                        {
                            result = counter.reset();
                            System.out.println("Zähler wurde zurückgesetzt");
                        }
                        else
                        {
                            result = counter.getCounter();
                        }
                        tcpSocket.sendLine("" + result);
                    }
                    else
                    {
                        System.out.println("Schließen der Verbindung");
                        break;
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
                System.out.println("=> Schließen der Verbindung");
            }
        }
    }
}
