package com.marvin_elsen.eva.uebung_10.aufgabe_01;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client
{
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException
    {
        Registry registry = LocateRegistry.getRegistry();

        AgentServer agentServer = (AgentServer) registry.lookup("AgentServer");

        Agent agent = new ConcreteAgent();
        agent = agentServer.takeAgent(agent);
        agent.doJob("PING PONG");
        Thread.sleep(10000);
        agent = agent.comeBack();
        System.out.println(agent.getInfo());
    }
}
