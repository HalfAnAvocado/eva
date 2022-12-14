package com.marvin_elsen.eva.uebung_06.aufgabe_06.server;


public class Counter
{
    private int counter;


    public synchronized int increment()
    {
        counter++;
        return counter;
    }


    public synchronized int reset()
    {
        counter = 0;
        return counter;
    }


    public synchronized int getCounter()
    {
        return counter;
    }
}
