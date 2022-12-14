package com.marvin_elsen.eva.uebung_02.aufgabe_01;


import java.io.Serial;
import java.io.Serializable;

// Sichtbarkeit eines Attributs ändern
//                  geht nicht
// mit serialUID:   geht
//
// Datentyp eines Attributs ändern
//                  geht nicht
// mit serialUID:   geht nicht
//
// Name eines Attributs ändern
//                  geht nicht
// mit serialUID:   geht
//
// Attribut hinzufügen
//                  geht nicht
// mit serialUID:   geht
//
// Attribut löschen
//                  geht nicht
// mit serialUID:   geht
//
// Reihenfolge der Attribute im Quellcode ändern
//                  geht
// mit serialUID:   geht
//
// Name einer Methode ändern
//                  geht nicht
// mit serialUID:   geht
//
// Methode hinzufügen
//                  geht nicht
// mit serialUID:   geht
//
// Methode löschen
//                  geht nicht
// mit serialUID:   geht
//
// Code einer Methode ändern
//                  geht
// mit serialUID:   geht
//
// Reihenfolge der Methoden im Quellcode ändern
//                  geht
// mit serialUID:   geht


public class Point implements Serializable
{
    @Serial
    private static final long serialVersionUID = 43981;

    private int x;
    private int y;


    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    public int getY()
    {
        return y;
    }


    public void setY(int y)
    {
        this.y = y;
    }


    public int getX()
    {
        return x;
    }


    public void setX(int x)
    {
        this.x = x;
    }


    @Override
    public String toString()
    {
        return "x: " + x + " y: " + y;
    }
}
