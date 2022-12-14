package com.marvin_elsen.eva.uebung_08.aufgabe_04_c;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client
{
    private final Registry registry;


    public Client(int registryPort, String[] arguments) throws RemoteException, NotBoundException, OverdrawAccountException
    {
        registry = LocateRegistry.getRegistry(registryPort);

        String command = arguments[0];
        int accountId = Integer.parseInt(arguments[1]);

        if (accountId < 0 || accountId >= 100)
        {
            throw new IllegalArgumentException();
        }

        Bank bank = (Bank) registry.lookup("Bank");

        if (command.equals("l"))
        {
            System.out.println("Kontostand des Kontos " + accountId + ": " + bank.getAccount(accountId).readBalance());
        }
        else if (command.equals("s"))
        {
            double newBalance = Double.parseDouble(arguments[2]);
            bank.getAccount(accountId).changeBalance(newBalance);
        }
    }
}
