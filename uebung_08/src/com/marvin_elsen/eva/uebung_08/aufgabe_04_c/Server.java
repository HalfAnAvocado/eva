package com.marvin_elsen.eva.uebung_08.aufgabe_04_c;


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


    public static void main(String[] args) throws RemoteException
    {
        LocateRegistry.createRegistry(1099);
        new Server(1099);
    }


    private void initializeRmiObjects() throws RemoteException
    {
        registry.rebind("Bank", new BankImpl());
    }
}
