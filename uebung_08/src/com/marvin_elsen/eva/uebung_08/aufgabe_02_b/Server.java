package com.marvin_elsen.eva.uebung_08.aufgabe_02_b;


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
        registry.rebind("AppendServer", new AppendImpl());
    }
}
