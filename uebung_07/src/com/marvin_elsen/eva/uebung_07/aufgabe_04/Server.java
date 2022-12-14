package com.marvin_elsen.eva.uebung_07.aufgabe_04;


import com.marvin_elsen.eva.uebung_07.aufgabe_04.data.Tiger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;


public class Server
{
    public static void main(String[] args)
    {
        try
        {
            Tiger tiger = new Tiger();
            Naming.rebind("Tiger", tiger);
            System.out.println("Server ready");
        }
        catch (RemoteException | MalformedURLException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
