package com.marvin_elsen.eva.uebung_08.aufgabe_04_c;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class BankImpl extends UnicastRemoteObject implements Bank
{
    @Serial
    private static final long serialVersionUID = 5499709034859630979L;

    private final List<Account> accounts = new ArrayList<>();


    public BankImpl() throws RemoteException
    {
        for (int i = 0; i < 100; i++)
        {
            accounts.add(new AccountImpl(0));
        }
    }


    public void addAccount(Account account)
    {
        accounts.add(account);
    }


    @Override
    public Account getAccount(int accountId) throws RemoteException
    {
        if (accountId < 0 || accountId >= accounts.size())
        {
            throw new IllegalArgumentException("Index out of bounds");
        }

        return accounts.get(accountId);
    }
}
