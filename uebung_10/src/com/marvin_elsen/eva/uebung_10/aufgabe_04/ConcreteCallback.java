package com.marvin_elsen.eva.uebung_10.aufgabe_04;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ConcreteCallback extends UnicastRemoteObject implements Callback
{
    @Serial
    private static final long serialVersionUID = -3518377878631952836L;


    protected ConcreteCallback() throws RemoteException
    {
    }


    @Override
    public void call()
    {
        System.out.println("- Called back -");
    }
}
