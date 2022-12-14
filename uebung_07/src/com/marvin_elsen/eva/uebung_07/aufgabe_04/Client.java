package com.marvin_elsen.eva.uebung_07.aufgabe_04;


import com.marvin_elsen.eva.uebung_07.aufgabe_04.data.Carnivore;
import com.marvin_elsen.eva.uebung_07.aufgabe_04.data.TerrestrialMammal;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


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
            Object tiger = Naming.lookup("rmi://" + args[0] + "/Tiger");

            System.out.println(((Carnivore) tiger).eatMeat());
            System.out.println(((TerrestrialMammal) tiger).walk());
        }
        catch (NotBoundException | MalformedURLException | RemoteException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
