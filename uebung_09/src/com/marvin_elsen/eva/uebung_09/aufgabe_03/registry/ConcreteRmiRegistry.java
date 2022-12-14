package com.marvin_elsen.eva.uebung_09.aufgabe_03.registry;


import java.io.Serial;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


public class ConcreteRmiRegistry extends UnicastRemoteObject implements RmiRegistry
{
    @Serial
    private static final long serialVersionUID = -2330675058671195649L;

    private final Map<String, Remote> stubMap = new HashMap<>();


    public ConcreteRmiRegistry() throws RemoteException
    {
    }


    @Override
    public synchronized void rebind(String name, Remote object)
    {
        stubMap.put(name, object);
    }


    @Override
    public synchronized Remote lookup(String name)
    {
        return stubMap.get(name);
    }
}
