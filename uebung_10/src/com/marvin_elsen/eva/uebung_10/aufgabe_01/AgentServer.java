package com.marvin_elsen.eva.uebung_10.aufgabe_01;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface AgentServer extends Remote
{
    Agent takeAgent(Agent agent) throws RemoteException;
}

