package com.marvin_elsen.eva.uebung_08.aufgabe_03;


import java.rmi.RemoteException;


public interface ChatClient extends java.rmi.Remote
{
    String getNickname() throws RemoteException;

    void print(String message) throws RemoteException;
}