package com.marvin_elsen.eva.uebung_07.aufgabe_02;


import com.marvin_elsen.eva.uebung_07.aufgabe_02.data.ConcreteCounter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;


public class Server
{
    public static void main(String[] args)
    {
        try
        {
            ConcreteCounter counter = new ConcreteCounter();
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("Counter", counter);
            RemoteServer.setLog(System.out);
            System.out.println("Counter server ready");
            //counter.reset();
        }
        catch (RemoteException | MalformedURLException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
