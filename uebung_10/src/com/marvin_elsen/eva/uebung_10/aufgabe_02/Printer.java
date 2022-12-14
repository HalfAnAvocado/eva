package com.marvin_elsen.eva.uebung_10.aufgabe_02;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Printer extends Remote
{
    void print() throws RemoteException;
}
