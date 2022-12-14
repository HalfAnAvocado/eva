package com.marvin_elsen.eva.uebung_10.aufgabe_01;


import java.io.Serial;
import java.io.Serializable;
import java.rmi.NoSuchObjectException;
import java.rmi.server.UnicastRemoteObject;


public class ConcreteAgent implements Agent, Serializable
{
    @Serial
    private static final long serialVersionUID = -7979533257451741995L;
    private String info;
    private transient Thread thread;


    @Override
    public void doJob(String heartbeat)
    {
        thread = new Thread(new AgentRunner(heartbeat));
        thread.start();
        info = System.getenv().keySet().toString();
    }


    @Override
    public String getInfo()
    {
        return info;
    }


    @Override
    public Agent comeBack()
    {
        thread.interrupt();
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        try
        {
            UnicastRemoteObject.unexportObject(this, true);
        }
        catch (NoSuchObjectException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return this;
    }
}
