package com.marvin_elsen.eva.uebung_08.aufgabe_03;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ConcreteChatClientSimple extends UnicastRemoteObject implements ChatClient
{
    @Serial
    private static final long serialVersionUID = -4912016591839392874L;

    private final String nickname;


    public ConcreteChatClientSimple(String nickname) throws RemoteException
    {
        this.nickname = nickname;
    }


    @Override
    public String getNickname() throws RemoteException
    {
        return nickname;
    }


    @Override
    public void print(String message) throws RemoteException
    {
        System.out.println(message);
    }
}
