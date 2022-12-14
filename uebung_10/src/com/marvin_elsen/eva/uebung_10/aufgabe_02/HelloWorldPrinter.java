package com.marvin_elsen.eva.uebung_10.aufgabe_02;


import java.io.Serial;
import java.io.Serializable;


public class HelloWorldPrinter implements Printer, Serializable
{
    @Serial
    private static final long serialVersionUID = 8200474679045367829L;


    @Override
    public void print()
    {
        System.out.println("Hello world!");
    }
}
