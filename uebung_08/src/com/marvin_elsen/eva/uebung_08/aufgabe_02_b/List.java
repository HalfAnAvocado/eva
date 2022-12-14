package com.marvin_elsen.eva.uebung_08.aufgabe_02_b;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class List extends UnicastRemoteObject implements ListInterface
{
    @Serial
    private static final long serialVersionUID = -7158850631838832244L;

    private ListItem first;
    private ListItem last;
    private int size;


    public List() throws RemoteException
    {
    }


    @Override
    public void append(int i)
    {
        if (first == null)
        {
            first = new ListItem(i);
            last = first;
        }
        else
        {
            last.setNext(new ListItem(i));
            last = last.getNext();
        }
        size++;
    }


    @Override
    public void print()
    {
        ListItem item = first;
        while (item != null)
        {
            System.out.print(item.getValue() + " ");
            item = item.getNext();
        }
        System.out.println();
    }


    @Override
    public ListItem getFirstListItem() throws RemoteException
    {
        return first;
    }


    @Override
    public int size() throws RemoteException
    {
        return size;
    }
}
