package com.marvin_elsen.eva.uebung_09.aufgabe_02.server;


import com.marvin_elsen.eva.uebung_09.aufgabe_02.client.Observer;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;


public class ConcreteObservable extends UnicastRemoteObject implements Observable
{
    @Serial
    private static final long serialVersionUID = 6044098839244816344L;

    private final List<Observer> observers = new LinkedList<>();


    public ConcreteObservable() throws RemoteException
    {
    }


    @Override
    public synchronized void register(Observer observer)
    {
        observers.add(observer);
    }


    public synchronized void notifyObservers(int sensorValue)
    {
        for (var observer : new LinkedList<>(observers))
        {
            try
            {
                if (observer.getIntervalStart() <= sensorValue && sensorValue <= observer.getIntervalEnd())
                {
                    observer.notifyValue(sensorValue);
                }
            }
            catch (RemoteException e)
            {
                observers.remove(observer);
            }
        }
    }
}
