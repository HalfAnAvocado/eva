package com.marvin_elsen.eva.uebung_10.aufgabe_04;


import java.rmi.Remote;
import java.rmi.RemoteException;


@FunctionalInterface
public interface Callback extends Remote
{
    void call() throws RemoteException;
}
