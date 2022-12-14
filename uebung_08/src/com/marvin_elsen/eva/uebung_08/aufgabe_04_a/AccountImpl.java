package com.marvin_elsen.eva.uebung_08.aufgabe_04_a;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class AccountImpl extends UnicastRemoteObject implements Account
{
    @Serial
    private static final long serialVersionUID = -4543248488602507981L;

    private double balance;


    public AccountImpl(double initialBalance) throws RemoteException
    {
        balance = initialBalance;
    }


    @Override
    public synchronized double readBalance()
    {
        return balance;
    }


    @Override
    public synchronized void changeBalance(double newBalance)
    {
        balance += newBalance;
    }
}
