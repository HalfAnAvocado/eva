package com.marvin_elsen.eva.uebung_08.aufgabe_01;


import com.marvin_elsen.eva.uebung_08.aufgabe_01.rmi.CounterStub;

import java.io.IOException;


public class Client
{
    public static void main(String[] args)
    {
        try
        {
            var counter1 = new CounterStub("localhost", 1099, "Counter1");
            var counter2 = new CounterStub("localhost", 1099, "Counter2");

            System.out.println("Setting counter1 to 0");
            counter1.reset();

            System.out.println("Setting counter2 to 0");
            counter2.reset();

            System.out.println("Incrementing");
            var count = 10;
            var result = 0;
            for (var i = 0; i < count; i++)
                result = counter1.increment();

            System.out.println("Counter1 = " + result);

            for (var i = 0; i < count * 2; i++)
                result = counter2.increment();

            System.out.println("Counter2 = " + result);
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
