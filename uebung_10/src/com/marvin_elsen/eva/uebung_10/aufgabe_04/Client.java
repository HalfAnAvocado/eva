package com.marvin_elsen.eva.uebung_10.aufgabe_04;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client
{
    public static void main(String[] args) throws RemoteException, NotBoundException
    {
        Registry registry = LocateRegistry.getRegistry();
        JobExecutor jobExecutor = (JobExecutor) registry.lookup("JobExecutor");

        jobExecutor.execute(1000, new ConcreteCallback());
        System.out.println("- Some other operations... -");
    }
}
