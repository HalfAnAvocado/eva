package com.marvin_elsen.eva.uebung_06.aufgabe_01;


import java.io.IOException;
import java.net.ServerSocket;


public class Server
{
    private static int counter;


    public static void runServer(ServerSocket socket)
    {
        System.out.println("Inside runServer");
        while (true)
        {
            System.out.println("Warten auf Verbindungsaufbau");
            try (TCPSocket tcpSocket = new TCPSocket(socket.accept()))
            {
                System.out.println("Verbindung akzeptiert");
                while (true)
                {
                    String request = tcpSocket.receiveLine();
                    if (request != null)
                    {
                        if (request.equals("increment"))
                        {
                            counter++;
                            //System.out.println("Der Zähler wurde erhöht");
                        }
                        else if (request.equals("decrement"))
                        {
                            counter--;
                        }
                        else if (request.startsWith("set"))
                        {
                            try
                            {
                                String[] splitString = request.split("\\s");
                                if (splitString.length == 2)
                                {
                                    counter = Integer.parseInt(splitString[1]);
                                }
                            }
                            catch (NumberFormatException e)
                            {
                                System.err.println("Ungültigen Wert bei set-Kommando erhalten");
                            }
                        }
                        else if (request.equals("reset"))
                        {
                            counter = 0;
                            System.out.println("Der Zähler wurde zurückgesetzt");
                        }
                        String result = String.valueOf(counter);
                        tcpSocket.sendLine(result);
                    }
                    else
                    {
                        System.out.println("Schließen der Verbindung");
                        break;
                    }
                }
            }
            catch (IOException e)
            {
                System.err.println(e);
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

        System.out.println("Create ServerSocket");

        // Backlog ist die größe der Queue, wie viele Clients akzeptiert werden vom OS, bevor der ServerSocket etwas akzeptiert
        try (ServerSocket sock = new ServerSocket(port, 2))
        {
            System.out.println("runServer");
            runServer(sock);
        }
    }
}
