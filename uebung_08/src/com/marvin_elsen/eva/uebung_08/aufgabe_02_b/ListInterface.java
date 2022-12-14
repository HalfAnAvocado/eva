package com.marvin_elsen.eva.uebung_08.aufgabe_02_b;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ListInterface extends Remote
{
    void append(int value) throws RemoteException;

    void print() throws RemoteException;

    ListItem getFirstListItem() throws RemoteException;

    int size() throws RemoteException;
}
