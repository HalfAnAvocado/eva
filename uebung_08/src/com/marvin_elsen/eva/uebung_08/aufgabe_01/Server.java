package com.marvin_elsen.eva.uebung_08.aufgabe_01;


import com.marvin_elsen.eva.uebung_08.aufgabe_01.data.ConcreteCounter;
import com.marvin_elsen.eva.uebung_08.aufgabe_01.data.Counter;
import com.marvin_elsen.eva.uebung_08.aufgabe_01.rmi.CounterSkeleton;

import java.io.IOException;
import java.util.Map;


public class Server
{
    public static void main(String[] args) throws IOException
    {
        var counters = Map.<String, Counter>of("Counter1", new ConcreteCounter(), "Counter2", new ConcreteCounter());

        var counterSkeleton = new CounterSkeleton(1099, counters);

        System.out.println("Server running...");
    }
}
