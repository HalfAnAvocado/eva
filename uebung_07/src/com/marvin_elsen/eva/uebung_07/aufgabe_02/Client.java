package com.marvin_elsen.eva.uebung_07.aufgabe_02;


import com.marvin_elsen.eva.uebung_07.aufgabe_02.common.Timer;
import com.marvin_elsen.eva.uebung_07.aufgabe_02.data.Counter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Client
{
    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            throw new IllegalArgumentException();
        }

        try
        {
            Counter counter = (Counter) Naming.lookup("rmi://" + args[0] + "/Counter");

            System.out.println("Setting counter to 0");
            counter.reset();
            System.out.println("Incrementing");

            int count = Integer.parseInt(args[1]);

            Timer timer = new Timer();

            int result = 0;
            for (int i = 0; i < count; i++)
                result = counter.increment();

            timer.stop();
            System.out.println("Elapsed time = " + timer.getElapsed() + " ms");

            if (count > 0)
            {
                System.out.println("Average ping = " + (timer.getElapsed() / (float) count) + " ms");
            }

            System.out.println("Counter = " + result);
        }
        catch (NotBoundException | MalformedURLException | RemoteException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
