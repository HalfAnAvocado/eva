package com.marvin_elsen.eva.uebung_09.aufgabe_02.client;


import com.marvin_elsen.eva.uebung_09.aufgabe_02.server.Observable;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Main
{
    public static void main(String[] args) throws UnknownHostException, RemoteException, NotBoundException, MalformedURLException
    {
        if (args.length != 4)
        {
            throw new IllegalArgumentException();
        }

        String registryAddress = args[0];
        int registryPort = Integer.parseInt(args[1]);
        int intervalStart = Integer.parseInt(args[2]);
        int intervalEnd = Integer.parseInt(args[3]);

        Observable observable = (Observable) Naming.lookup("rmi://" + registryAddress + "/Observable");
        observable.register(new ConcreteObserver(0, 50));
        observable.register(new ConcreteObserver(51, 100));
    }
}
