package com.marvin_elsen.eva.uebung_10.aufgabe_02;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server
{
    public static void main(String[] args) throws RemoteException
    {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("Printer", new HelloWorldPrinter());
        System.out.println("- Server started... - ");

        // Sonst terminiert Server direkt
        while (true)
        {

        }
    }
}
