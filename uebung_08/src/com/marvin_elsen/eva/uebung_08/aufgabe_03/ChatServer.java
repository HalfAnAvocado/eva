package com.marvin_elsen.eva.uebung_08.aufgabe_03;


import java.rmi.RemoteException;


public interface ChatServer extends java.rmi.Remote
{
    boolean addClient(ChatClient chatClient) throws RemoteException;

    void removeClient(ChatClient chatClient) throws RemoteException;

    void sendMessage(String senderName, String message) throws RemoteException;
}