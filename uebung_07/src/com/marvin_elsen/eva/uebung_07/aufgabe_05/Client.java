package com.marvin_elsen.eva.uebung_07.aufgabe_05;


import com.marvin_elsen.eva.uebung_07.aufgabe_05.data.Echo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;


public class Client
{
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException();
        }

        try
        {
            System.out.println(Arrays.toString(Naming.list("rmi://" + args[0])));

            Echo echo1 = (Echo) Naming.lookup("rmi://" + args[0] + "/Echo1");
            Echo echo2 = (Echo) Naming.lookup("rmi://" + args[0] + "/Echo1");

            System.out.println();
            System.out.println("echo1 und echo2 verweisen auf dasselbe RMI-Objekt:");
            System.out.println("echo1 == echo2: " + (echo1 == echo2));
            System.out.println("echo1.equals(echo2): " + (echo1.equals(echo2)));

            echo2 = (Echo) Naming.lookup("rmi://" + args[0] + "/Echo2");

            System.out.println();
            System.out.println("echo1 und echo2 verweisen auf unterschiedliche RMI-Objekt:");
            System.out.println("echo1 == echo2: " + (echo1 == echo2));
            System.out.println("echo1.equals(echo2): " + (echo1.equals(echo2)));
        }
        catch (NotBoundException | MalformedURLException | RemoteException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
