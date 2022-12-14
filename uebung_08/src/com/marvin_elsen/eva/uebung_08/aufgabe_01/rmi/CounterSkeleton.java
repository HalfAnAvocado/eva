package com.marvin_elsen.eva.uebung_08.aufgabe_01.rmi;


import com.marvin_elsen.eva.uebung_08.aufgabe_01.data.Counter;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;


public class CounterSkeleton
{
    private final Map<String, Counter> counters;
    private final ServerSocket serverSocket;


    public CounterSkeleton(int port, Map<String, Counter> counters) throws IOException
    {
        serverSocket = new ServerSocket(port);
        this.counters = counters;

        run();
    }


    private void run() throws IOException
    {
        while (true)
            new Thread(new Handler(serverSocket.accept(), counters)).start();
    }
}
