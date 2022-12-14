package com.marvin_elsen.eva.uebung_06.aufgabe_06;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class TCPRelay
{
    public static void main(String[] args) throws UnknownHostException
    {
        if (args.length != 3)
        {
            throw new IllegalArgumentException();
        }

        int port = Integer.parseInt(args[0]);
        InetAddress serverAddress = InetAddress.getByName(args[1]);
        int serverPort = Integer.parseInt(args[2]);

        try (ServerSocket listeningSocket = new ServerSocket(port))
        {
            while (true)
            {
                try
                {
                    Socket clientSocket = listeningSocket.accept();

                    new Handler(serverAddress, serverPort, clientSocket);
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
