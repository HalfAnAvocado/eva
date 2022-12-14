package com.marvin_elsen.eva.uebung_10.aufgabe_01;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ConcreteAgentServer extends UnicastRemoteObject implements AgentServer
{
    @Serial
    private static final long serialVersionUID = -2703349621606595306L;


    protected ConcreteAgentServer() throws RemoteException
    {
    }


    @Override
    public Agent takeAgent(Agent agent)
    {
        try
        {
            UnicastRemoteObject.exportObject(agent, 0);
        }
        catch (RemoteException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return agent;
    }
}
