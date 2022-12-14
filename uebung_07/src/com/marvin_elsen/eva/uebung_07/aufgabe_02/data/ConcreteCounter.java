package com.marvin_elsen.eva.uebung_07.aufgabe_02.data;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
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

        printClientHost();

        return count;
    }


    private void printClientHost()
    {
        try
        {
            System.out.println("ClientHost: " + RemoteServer.getClientHost());
        }
        catch (ServerNotActiveException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public synchronized int increment() throws RemoteException
    {
        count++;

        printClientHost();

        return count;
    }
}
