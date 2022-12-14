package com.marvin_elsen.eva.uebung_06.aufgabe_03;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(4444);

            while (true)
            {
                try (Socket socket = serverSocket.accept())
                {
                    while (true)
                    {
                        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream())))
                        {
                            Node root = (Node) objectInputStream.readObject();
                            root.print();
                        }
                        catch (ClassNotFoundException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
