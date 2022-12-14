package com.marvin_elsen.eva.uebung_09.aufgabe_01;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ConcreteMessageQueue extends UnicastRemoteObject implements RmiMessageQueue
{
    @Serial
    private static final long serialVersionUID = -3219069834444604964L;

    private final int capacity;
    private final byte[][] messages;
    private int size;
    private int beginning;
    private int end;


    public ConcreteMessageQueue(int capacity) throws RemoteException
    {
        messages = new byte[capacity][];
        this.capacity = capacity;
    }


    private boolean isFull()
    {
        return size == capacity;
    }


    private boolean isEmpty()
    {
        return size == 0;
    }


    @Override
    public synchronized void put(byte[] message)
    {
        while (isFull())
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        messages[end] = message;
        end = (end + 1) % capacity;
        size++;

        notifyAll();
    }


    @Override
    public synchronized byte[] take()
    {
        while (isEmpty())
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        byte[] message = messages[beginning];
        beginning = (beginning + 1) % capacity;
        size--;

        notifyAll();

        return message;
    }
}
