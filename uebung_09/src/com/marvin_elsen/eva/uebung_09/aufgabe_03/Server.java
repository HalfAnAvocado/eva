package com.marvin_elsen.eva.uebung_09.aufgabe_03;


import com.marvin_elsen.eva.uebung_09.aufgabe_03.data.ConcreteCounter;
import com.marvin_elsen.eva.uebung_09.aufgabe_03.registry.RmiRegistry;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;


public class Server
{
    public static void main(String[] args) throws IOException, NotBoundException
    {
        RmiRegistry registry = (RmiRegistry) Naming.lookup("Registry");
        registry.rebind("Counter", new ConcreteCounter());

        System.out.println("Server running...");
    }
}
