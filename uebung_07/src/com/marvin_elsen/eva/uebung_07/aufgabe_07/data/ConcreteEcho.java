package com.marvin_elsen.eva.uebung_07.aufgabe_07.data;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ConcreteEcho extends UnicastRemoteObject implements Echo
{
    @Serial
    private static final long serialVersionUID = -4927694896843564331L;


    public ConcreteEcho() throws RemoteException
    {
    }


    @Override
    public String echo(String message) throws RemoteException
    {
        System.out.println("Thread name: " + Thread.currentThread().getName());
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return message;
    }
}
