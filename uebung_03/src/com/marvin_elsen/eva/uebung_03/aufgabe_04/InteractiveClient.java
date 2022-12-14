package com.marvin_elsen.eva.uebung_03.aufgabe_04;


import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class InteractiveClient
{
    private static final int TIMEOUT = 60_000;
    private static final boolean isRunning = true;


    public static void main(String[] args) throws UnknownHostException
    {
        if (args.length != 2)
        {
            throw new IllegalArgumentException("ARGS: <ADDRESS> <PORT>");
        }

        InetAddress serverAddr = InetAddress.getByName(args[0]);
        int port = Integer.parseInt(args[1]);

        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
             UDPSocket udpSocket = new UDPSocket())
        {
            udpSocket.setTimeOut(TIMEOUT);

            while (isRunning)
            {
                System.out.print("Kommando: ");
                String command = stdIn.readLine();

                if (command.equalsIgnoreCase("exit"))
                {
                    System.exit(0);
                }
                else
                {
                    int commandValue = 0;
                    int setValue = Integer.MAX_VALUE;
                    if (command.equals("increment"))
                    {
                        commandValue = 1;
                    }
                    else if (command.equals("decrement"))
                    {
                        commandValue = 2;
                    }
                    else if (command.startsWith("set"))
                    {
                        commandValue = 3;

                        try
                        {
                            setValue =
                                    Integer.parseInt(command.substring(command.indexOf(' ') + 1));
                        }
                        catch (NumberFormatException e)
                        {
                            System.err.println(e.getMessage());
                        }
                    }

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
                    try (DataOutputStream dataOutputStream = new DataOutputStream(
                            byteArrayOutputStream))
                    {
                        dataOutputStream.writeByte(commandValue);

                        if (commandValue == 3 && setValue != Integer.MAX_VALUE)
                        {
                            dataOutputStream.writeInt(setValue);
                        }

                        udpSocket.send(byteArrayOutputStream.toByteArray(), serverAddr, port);
                    }

                    byte[] reply;

                    reply = udpSocket.receive(100);

                    try (DataInputStream inputStream = new DataInputStream(
                            new ByteArrayInputStream(reply)))
                    {
                        int counter = inputStream.readInt();

                        System.out.println("counter = " + counter);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
