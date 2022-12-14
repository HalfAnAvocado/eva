package com.marvin_elsen.eva.uebung_08.aufgabe_03;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;


public class ChatClientSimpleMain
{
    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            throw new IllegalArgumentException("usage: java rmi.chat.ChatClientMainSimple <server> <nick name> <group>");
        }

        try
        {
            ChatServer server = (ChatServer) Naming.lookup("rmi://" + args[0] + "/" + args[2]);

            System.out.println("Server contacted.");

            ConcreteChatClientSimple client = new ConcreteChatClientSimple(args[1]);

            if (server.addClient(client))
            {
                System.out.println("End by typing 'exit' or 'quit'.");
                sendInputToServer(server, args[1]);
                server.removeClient(client);
            }
            else
            {
                System.out.println("name already defined at server");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.exit(0);
    }


    private static void sendInputToServer(ChatServer server, String name)
    {
        try
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = input.readLine()) != null)
            {
                if (line.equals("exit") || line.equals("quit"))
                {
                    break;
                }
                server.sendMessage(name, line);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
