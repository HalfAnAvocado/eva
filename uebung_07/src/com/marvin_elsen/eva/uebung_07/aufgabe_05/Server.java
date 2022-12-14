package com.marvin_elsen.eva.uebung_07.aufgabe_05;


import com.marvin_elsen.eva.uebung_07.aufgabe_05.data.ConcreteEcho;
import com.marvin_elsen.eva.uebung_07.aufgabe_05.data.Echo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;


public class Server
{
    public static void main(String[] args)
    {
        try
        {
            Echo echo1 = new ConcreteEcho();
            Echo echo2 = new ConcreteEcho();

            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("Echo1", echo1);
            Naming.rebind("Echo2", echo2);

            System.out.println("Server ready");
            System.out.println(Arrays.toString(Naming.list("localhost")));
        }
        catch (RemoteException | MalformedURLException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
