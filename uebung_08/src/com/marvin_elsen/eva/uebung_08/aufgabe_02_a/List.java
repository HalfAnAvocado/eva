package com.marvin_elsen.eva.uebung_08.aufgabe_02_a;


import java.io.Serial;
import java.io.Serializable;
import java.rmi.RemoteException;


public class List implements Serializable
{
    @Serial
    private static final long serialVersionUID = -7158850631838832244L;

    private ListItem first;
    private ListItem last;
    private int size;


    public List() throws RemoteException
    {
    }


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


    public ListItem getFirstListItem() throws RemoteException
    {
        return first;
    }


    public int size() throws RemoteException
    {
        return size;
    }
}
