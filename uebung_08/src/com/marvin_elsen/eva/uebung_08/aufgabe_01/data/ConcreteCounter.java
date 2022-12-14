package com.marvin_elsen.eva.uebung_08.aufgabe_01.data;


import java.rmi.RemoteException;


public class ConcreteCounter implements Counter
{
    private static final long serialVersionUID = 5720717297796403812L;
    private int count;


    public ConcreteCounter()
    {
    }


    @Override
    public synchronized int reset()
    {
        count = 0;

        return count;
    }


    @Override
    public synchronized int increment() throws RemoteException
    {
        count++;

        return count;
    }
}
