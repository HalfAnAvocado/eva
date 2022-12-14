package com.marvin_elsen.eva.uebung_07.aufgabe_03;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server
{
    private Registry registry;


    Server(int registryPort)
    {
        try
        {
            registry = LocateRegistry.getRegistry(registryPort);
            run();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }


    private void run()
    {
        try
        {
            MultiplyImpl multiply = new MultiplyImpl();
            registry.rebind("MultiplyServer", multiply);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
