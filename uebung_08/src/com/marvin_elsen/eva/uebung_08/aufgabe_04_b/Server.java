package com.marvin_elsen.eva.uebung_08.aufgabe_04_b;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server
{
    private final Registry registry;


    public Server(int registryPort) throws RemoteException
    {
        registry = LocateRegistry.getRegistry(registryPort);
        initializeRmiObjects();
    }


    private void initializeRmiObjects() throws RemoteException
    {
        for (int i = 0; i < 100; i++)
        {
            registry.rebind("Konto" + i, new AccountImpl(0));
        }
    }
}
