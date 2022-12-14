package com.marvin_elsen.eva.uebung_06.aufgabe_03;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client
{
    public static void main(String[] args)
    {
        Node root = new Node(0);
        Node currentNode = root;
        for (int i = 1; i <= 200; i++)
        {
            Node leftNode = new Node(i);
            currentNode.addChild(leftNode);
            Node rightNode = new Node(i + 1);
            currentNode.addChild(rightNode);

            currentNode = Math.random() <= 0.5 ? leftNode : rightNode;
        }

        root.print();
        try
        {
            Socket socket = new Socket("localhost", 4444);

            try (ObjectOutputStream objectOutputStream =
                         new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream())))
            {
                objectOutputStream.writeObject(root);
                objectOutputStream.flush();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
