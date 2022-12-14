package com.marvin_elsen.eva.uebung_08.aufgabe_02_a;


import java.io.Serial;
import java.io.Serializable;


class ListItem implements Serializable
{
    @Serial
    private static final long serialVersionUID = -1201201718366267204L;
    private final int value;
    private ListItem next;


    public ListItem(int v)
    {
        value = v;
        next = null;
    }


    public int getValue()
    {
        return value;
    }


    public ListItem getNext()
    {
        return next;
    }


    public void setNext(ListItem n)
    {
        next = n;
    }
}
