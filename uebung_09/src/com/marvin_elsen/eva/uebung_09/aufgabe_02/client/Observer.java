package com.marvin_elsen.eva.uebung_09.aufgabe_02.client;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Observer extends Remote
{
    void notifyValue(int sensorValue) throws RemoteException;

    int getIntervalStart() throws RemoteException;

    int getIntervalEnd() throws RemoteException;
}
