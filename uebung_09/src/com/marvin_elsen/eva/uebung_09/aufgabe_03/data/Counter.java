package com.marvin_elsen.eva.uebung_09.aufgabe_03.data;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Counter extends Remote
{
    int reset() throws RemoteException;

    int increment() throws RemoteException;
}
