package com.marvin_elsen.eva.uebung_08.aufgabe_02_b;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Append extends Remote
{
    void append(ListInterface l) throws RemoteException;
}

