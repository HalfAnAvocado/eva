package com.marvin_elsen.eva.uebung_07.aufgabe_06;


import com.marvin_elsen.eva.uebung_07.aufgabe_06.data.ConcreteEcho;
import com.marvin_elsen.eva.uebung_07.aufgabe_06.data.Echo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;


public class Server
{
    public static void main(String[] args)
    {
        try
        {
            Echo echo = new ConcreteEcho();

            Naming.rebind("rmi://192.168.2.113/Echo", echo);

            System.out.println("Server ready");
        }
        catch (RemoteException | MalformedURLException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
