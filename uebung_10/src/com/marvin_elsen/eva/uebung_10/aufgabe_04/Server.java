package com.marvin_elsen.eva.uebung_10.aufgabe_04;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server
{
    public static void main(String[] args) throws RemoteException
    {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("JobExecutor", new ConcreteJobExecutor());
        System.out.println("- Server running... -");
    }
}
