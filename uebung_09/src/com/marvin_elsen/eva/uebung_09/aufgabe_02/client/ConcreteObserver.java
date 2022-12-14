package com.marvin_elsen.eva.uebung_09.aufgabe_02.client;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ConcreteObserver extends UnicastRemoteObject implements Observer
{
    @Serial
    private static final long serialVersionUID = -4024347626273597808L;

    private final int intervalStart;
    private final int intervalEnd;


    public ConcreteObserver(int intervalStart, int intervalEnd) throws RemoteException
    {
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
    }


    @Override
    public void notifyValue(int sensorValue) throws RemoteException
    {
        System.out.println("Received sensor value: " + sensorValue);
    }


    @Override
    public int getIntervalStart() throws RemoteException
    {
        return intervalStart;
    }


    @Override
    public int getIntervalEnd() throws RemoteException
    {
        return intervalEnd;
    }
}
