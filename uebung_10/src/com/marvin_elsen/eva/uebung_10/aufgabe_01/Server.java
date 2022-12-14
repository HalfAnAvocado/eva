package com.marvin_elsen.eva.uebung_10.aufgabe_01;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server
{
    public static void main(String[] args) throws RemoteException
    {
        Registry registry = LocateRegistry.createRegistry(1099);

        AgentServer agentServer = new ConcreteAgentServer();

        registry.rebind("AgentServer", agentServer);

        System.out.println("Server gestartet");
    }
}
