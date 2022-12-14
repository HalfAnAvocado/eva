package com.marvin_elsen.eva.uebung_07.aufgabe_01;


import com.marvin_elsen.eva.uebung_07.aufgabe_01.data.ConcreteCounter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;


public class Server
{
    public static void main(String[] args)
    {
        try
        {
            ConcreteCounter counter = new ConcreteCounter();
            Naming.rebind("Counter", counter);
            System.out.println("Counter server ready");
        }
        catch (RemoteException | MalformedURLException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
