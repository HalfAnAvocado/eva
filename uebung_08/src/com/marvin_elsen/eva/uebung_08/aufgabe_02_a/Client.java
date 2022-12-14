package com.marvin_elsen.eva.uebung_08.aufgabe_02_a;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client
{
    private final Registry registry;


    public Client(int registryPort) throws RemoteException, NotBoundException
    {
        registry = LocateRegistry.getRegistry(registryPort);

        Append server = (Append) registry.lookup("AppendServer");

        System.out.println("Server contacted");

        List l = new List();
        for (int i = 0; i < 100; i++)
        {
            l.append(i);
        }
        System.out.print("list before RMI: ");
        l.print();
        l = server.append(l);
        System.out.print("list after RMI: ");
        l.print();
    }


    public static void main(String[] args) throws RemoteException, NotBoundException
    {
        Client client = new Client(1099);
    }
}