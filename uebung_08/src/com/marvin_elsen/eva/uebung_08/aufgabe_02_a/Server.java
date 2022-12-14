package com.marvin_elsen.eva.uebung_08.aufgabe_02_a;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server
{
    private final Registry registry;


    public Server(int registryPort) throws RemoteException
    {
        registry = LocateRegistry.createRegistry(registryPort);
        initializeRmiObjects();
    }


    public static void main(String[] args) throws RemoteException
    {
        Server server = new Server(1099);
    }


    private void initializeRmiObjects() throws RemoteException
    {
        registry.rebind("AppendServer", new AppendImpl());
    }
}
