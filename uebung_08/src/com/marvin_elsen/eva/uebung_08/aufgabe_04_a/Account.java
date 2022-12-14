package com.marvin_elsen.eva.uebung_08.aufgabe_04_a;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Account extends Remote
{
    double readBalance() throws RemoteException;

    void changeBalance(double balance) throws RemoteException;
}
