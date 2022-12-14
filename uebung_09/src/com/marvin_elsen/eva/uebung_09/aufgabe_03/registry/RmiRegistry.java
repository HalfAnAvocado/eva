package com.marvin_elsen.eva.uebung_09.aufgabe_03.registry;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RmiRegistry extends Remote
{
    void rebind(String name, Remote object) throws RemoteException;

    Remote lookup(String name) throws RemoteException;
}