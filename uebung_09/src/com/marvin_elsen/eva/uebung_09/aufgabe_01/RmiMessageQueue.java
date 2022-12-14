package com.marvin_elsen.eva.uebung_09.aufgabe_01;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RmiMessageQueue extends Remote
{
    void put(byte[] message) throws RemoteException;

    byte[] take() throws RemoteException;
}
