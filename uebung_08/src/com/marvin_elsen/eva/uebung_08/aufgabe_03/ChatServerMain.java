package com.marvin_elsen.eva.uebung_08.aufgabe_03;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ChatServerMain
{
    public static void main(String[] args)
    {
        try
        {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatServer1", new ConcreteChatServer());
            registry.rebind("ChatServer2", new ConcreteChatServer());
            registry.rebind("ChatServer3", new ConcreteChatServer());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}