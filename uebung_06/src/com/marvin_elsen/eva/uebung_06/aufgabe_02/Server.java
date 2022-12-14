package com.marvin_elsen.eva.uebung_06.aufgabe_02;


import java.io.*;
import java.net.ServerSocket;


public class Server
{
    private static int counter;


    public static void runServer(ServerSocket socket)
    {
        while (true)
        {
            System.out.println("Warten auf Verbindungsaufbau");
            try (TCPSocketBinary tcpSocket = new TCPSocketBinary(socket.accept()))
            {
                System.out.println("Verbindung akzeptiert");

                loop:
                while (true)
                {
                    byte[] request = tcpSocket.receive();
                    if (request == null)
                    {
                        System.out.println("Schließen der Verbindung");
                        break;
                    }

                    try (DataInputStream dataInputStream =
                                 new DataInputStream(new ByteArrayInputStream(request));
                         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4);
                         DataOutputStream dataOutputStream =
                                 new DataOutputStream(byteArrayOutputStream))
                    {

                        Protocol command = Protocol.values()[dataInputStream.readInt()];
                        switch (command)
                        {
                            case INCREMENT:
                                counter++;
                                break;

                            case DECREMENT:
                                counter--;
                                break;

                            case SET:
                                counter = dataInputStream.readInt();
                                break;

                            case RESET:
                                counter = 0;
                                break;

                            default:
                                System.out.println("Schließen der Verbindung");
                                break loop;
                        }

                        dataOutputStream.writeInt(counter);
                        tcpSocket.send(byteArrayOutputStream.toByteArray());
                    }
                }
            }
            catch (IOException e)
            {
                System.err.println(e.getMessage());
                System.err.println("=> Verbindung geschlossen");
            }
        }
    }


    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException();
        }

        int port = Integer.parseInt(args[0]);

        try (ServerSocket sock = new ServerSocket(port, 2))
        {
            runServer(sock);
        }
    }
}
