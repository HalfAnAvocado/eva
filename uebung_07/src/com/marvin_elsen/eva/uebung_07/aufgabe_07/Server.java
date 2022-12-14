package com.marvin_elsen.eva.uebung_07.aufgabe_07;


import com.marvin_elsen.eva.uebung_07.aufgabe_07.data.ConcreteEcho;
import com.marvin_elsen.eva.uebung_07.aufgabe_07.data.Echo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server
{
    public static void main(String[] args)
    {
        try
        {
            Echo echo = new ConcreteEcho();

            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("Echo", echo);

            System.out.println("Server ready");
        }
        catch (RemoteException | MalformedURLException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
