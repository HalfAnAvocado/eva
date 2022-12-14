package com.marvin_elsen.eva.uebung_07.aufgabe_03;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Multiply extends Remote
{
    int mult(int a, int b) throws RemoteException;
}
