package com.marvin_elsen.eva.uebung_09.aufgabe_02.server;


import com.marvin_elsen.eva.uebung_09.aufgabe_02.client.Observer;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Observable extends Remote
{
    void register(Observer observer) throws RemoteException;
}
