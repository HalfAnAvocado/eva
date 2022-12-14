package com.marvin_elsen.eva.uebung_10.aufgabe_04;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ConcreteJobExecutor extends UnicastRemoteObject implements JobExecutor
{
    @Serial
    private static final long serialVersionUID = 7298593172511852694L;


    protected ConcreteJobExecutor() throws RemoteException
    {
    }


    @Override
    public void execute(int sleepMs, Callback callback)
    {
        new Thread(() ->
        {
            try
            {
                Thread.sleep(sleepMs);
                callback.call();
            }
            catch (InterruptedException | RemoteException e)
            {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }
}
