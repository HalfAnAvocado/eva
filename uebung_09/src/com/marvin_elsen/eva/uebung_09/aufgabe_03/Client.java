package com.marvin_elsen.eva.uebung_09.aufgabe_03;


import com.marvin_elsen.eva.uebung_09.aufgabe_03.data.Counter;
import com.marvin_elsen.eva.uebung_09.aufgabe_03.registry.RmiRegistry;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;


public class Client
{
    public static void main(String[] args)
    {
        try
        {
            RmiRegistry registry = (RmiRegistry) Naming.lookup("Registry");
            Counter counter = (Counter) registry.lookup("Counter");

            System.out.println("Setting counter to 0");
            counter.reset();

            System.out.println("Incrementing");
            var count = 10;
            var result = 0;
            for (var i = 0; i < count; i++) result = counter.increment();

            System.out.println("Counter = " + result);
        }
        catch (IOException | NotBoundException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
