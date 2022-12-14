package com.marvin_elsen.eva.uebung_10.aufgabe_01;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Agent extends Remote
{
    void doJob(String heartbeat) throws RemoteException;

    String getInfo() throws RemoteException;

    Agent comeBack() throws RemoteException;
}
