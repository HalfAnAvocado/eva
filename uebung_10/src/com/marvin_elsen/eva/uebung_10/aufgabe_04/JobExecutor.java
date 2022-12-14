package com.marvin_elsen.eva.uebung_10.aufgabe_04;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface JobExecutor extends Remote
{
    void execute(int sleepMs, Callback callback) throws RemoteException;
}