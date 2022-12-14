package com.marvin_elsen.eva.uebung_07.aufgabe_04.data;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface TerrestrialMammal extends Remote
{
    String walk() throws RemoteException;
}
