package com.marvin_elsen.eva.uebung_10.aufgabe_02;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client
{
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException
    {
        Registry registry = LocateRegistry.getRegistry();

        // Cast funktioniert
        HelloWorldPrinter printer = (HelloWorldPrinter) registry.lookup("Printer");

        while (true)
        {
            Thread.sleep(1000);

            // Funktioniert auch nachdem Server terminiert wurde
            // Ausgabe auf dem Client, statt auf dem Server
            printer.print();
        }
    }
}
