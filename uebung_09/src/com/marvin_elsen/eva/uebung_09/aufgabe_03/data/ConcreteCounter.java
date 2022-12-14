package com.marvin_elsen.eva.uebung_09.aufgabe_03.data;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ConcreteCounter extends UnicastRemoteObject implements Counter
{
    @Serial
    private static final long serialVersionUID = 5720717297796403812L;
    private int count;


    public ConcreteCounter() throws RemoteException
    {
    }


    @Override
    public synchronized int reset() throws RemoteException
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
