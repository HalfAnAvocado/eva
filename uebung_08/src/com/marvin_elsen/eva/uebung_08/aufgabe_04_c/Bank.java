package com.marvin_elsen.eva.uebung_08.aufgabe_04_c;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Bank extends Remote
{
    Account getAccount(int accountId) throws RemoteException;
}
