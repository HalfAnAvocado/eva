package com.marvin_elsen.eva.uebung_06.aufgabe_06;


import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class Handler extends Thread
{
    private final Socket serverSocket;


    public Handler(InetAddress serverAddress, int serverPort, Socket clientSocket)
            throws IOException
    {
        serverSocket = new Socket(serverAddress, serverPort);

        new Thread(() ->
        {
            try
            {
                byte[] buffer = new byte[100];
                int bytesRead;
                while ((bytesRead = clientSocket.getInputStream().read(buffer)) != -1)
                {
                    serverSocket.getOutputStream().write(buffer, 0, bytesRead);

                    byte[] data = new byte[bytesRead];
                    System.arraycopy(buffer, 0, data, 0, bytesRead);

                    System.out.format("C[%s:%s] --- 0x%s %s ---> S[%s:%s]\n",
                            clientSocket.getInetAddress().getHostAddress(),
                            clientSocket.getPort(),
                            Hex.encodeHexString(data),
                            new String(data),
                            serverAddress.getHostAddress(),
                            serverPort);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    serverSocket.shutdownOutput();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() ->
        {
            try
            {
                byte[] buffer = new byte[100];
                int bytesRead;
                while ((bytesRead = serverSocket.getInputStream().read(buffer)) != -1)
                {
                    clientSocket.getOutputStream().write(buffer, 0, bytesRead);

                    byte[] data = new byte[bytesRead];
                    System.arraycopy(buffer, 0, data, 0, bytesRead);

                    System.out.format("C[%s:%s] <--- 0x%s %s --- S[%s:%s]\n",
                            clientSocket.getInetAddress().getHostAddress(),
                            clientSocket.getPort(),
                            Hex.encodeHexString(data),
                            new String(data),
                            serverAddress.getHostAddress(),
                            serverPort);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    clientSocket.shutdownOutput();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
