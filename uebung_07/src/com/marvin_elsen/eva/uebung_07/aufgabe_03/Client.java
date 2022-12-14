package com.marvin_elsen.eva.uebung_07.aufgabe_03;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client
{
    private Registry registry;
    private Multiply multiply;


    Client(int registryPort)
    {
        try
        {
            registry = LocateRegistry.getRegistry(registryPort);
            multiply = (Multiply) registry.lookup("MultiplyServer");
            run();
        }
        catch (RemoteException | NotBoundException e)
        {
            e.printStackTrace();
        }
    }


    private void run()
    {
        for (int i = 1; i <= 10; i++)
        {
            for (int j = 1; j <= 10; j++)
            {
                try
                {
                    System.out.println(i + " x " + j + " = " + multiply.mult(i, j));
                }
                catch (RemoteException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
