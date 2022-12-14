package com.marvin_elsen.eva.uebung_07.aufgabe_03;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class MultiplyImpl extends UnicastRemoteObject implements Multiply
{
    @Serial
    private static final long serialVersionUID = 1894427844852099148L;


    public MultiplyImpl() throws RemoteException
    {
    }


    @Override
    public int mult(int a, int b) throws RemoteException
    {
        return a * b;
    }
}
