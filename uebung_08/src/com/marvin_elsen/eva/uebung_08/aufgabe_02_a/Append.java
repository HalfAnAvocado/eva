package com.marvin_elsen.eva.uebung_08.aufgabe_02_a;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Append extends Remote
{
    List append(List l) throws RemoteException;
}

