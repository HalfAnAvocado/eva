package com.marvin_elsen.eva.uebung_08.aufgabe_02_a;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class AppendImpl extends UnicastRemoteObject implements Append
{
    @Serial
    private static final long serialVersionUID = 6726890808201119043L;


    public AppendImpl() throws RemoteException
    {
    }


    @Override
    public List append(List l) throws RemoteException
    {
        System.out.print("got list: ");
        l.print();

        l.append(4711);
        System.out.print("list manipulated: ");
        l.print();

        return l;
    }
}
