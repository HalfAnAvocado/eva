package com.marvin_elsen.eva.uebung_02.aufgabe_03;


import com.marvin_elsen.eva.uebung_02.aufgabe_02.Node;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

// -Xss
// -X bedeutet "JVM Option"
// ss bedeutet "Stack Size"
// -Xss10m Um die Stackgröße auf 10MB zu vergößern. Default-Wert Plattform und JVM abhängig.
// Auf Linux IA64 => 1 MB


// Alternative:
// Für jede Referenz einzelt writeObject() aufrufen und beim Einlesen diese entsprechend zuweisen
// Rückwärts speichern und einlesen
// Transient verwenden und die Referenzen einzelt schreiben
public class Main
{
    public static void main(String[] args)
    {
        final String FILE_NAME = "linked_list.bin";
        final int valuesCount = 10_000;

        Node a = new Node(0);
        Node lastNode = a;

        for (int i = 1; i <= valuesCount; i++)
        {
            Node newNode = new Node(i);
            lastNode.setNextNode(newNode);
            lastNode = newNode;
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(
                        FILE_NAME))))
        {
            outputStream.writeObject(a);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
