package com.marvin_elsen.eva.uebung_09.aufgabe_01;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server
{
    public static void main(String[] args) throws RemoteException
    {
        Registry registry = LocateRegistry.createRegistry(1099);

        registry.rebind("Queue", new ConcreteMessageQueue(10));
    }
}
