package com.marvin_elsen.eva.uebung_07.aufgabe_07;


import com.marvin_elsen.eva.uebung_07.aufgabe_07.data.Echo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Client
{
    public static void main(String[] args)
    {
        try
        {
            Echo echo = (Echo) Naming.lookup("rmi://localhost/Echo");

            for (int i = 0; i < 100; i++)
                System.out.println(echo.echo("Hallo Welt!"));
        }
        catch (NotBoundException | MalformedURLException | RemoteException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
