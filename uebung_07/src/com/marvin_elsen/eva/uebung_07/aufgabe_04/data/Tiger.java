package com.marvin_elsen.eva.uebung_07.aufgabe_04.data;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Tiger extends UnicastRemoteObject implements TerrestrialMammal, Carnivore
{
    @Serial
    private static final long serialVersionUID = -8044424634204515358L;


    public Tiger() throws RemoteException
    {
    }


    @Override
    public String walk() throws RemoteException
    {
        return "The tiger walks on its four legs.";
    }


    @Override
    public String eatMeat() throws RemoteException
    {
        return "The tiger eats meat.";
    }
}
