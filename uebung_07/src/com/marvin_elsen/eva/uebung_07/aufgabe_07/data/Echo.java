package com.marvin_elsen.eva.uebung_07.aufgabe_07.data;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Echo extends Remote
{
    String echo(String message) throws RemoteException;
}
