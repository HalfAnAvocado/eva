package com.marvin_elsen.eva.uebung_08.aufgabe_03;


import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ConcreteChatServer extends UnicastRemoteObject implements ChatServer
{
    @Serial
    private static final long serialVersionUID = 4497583075537288230L;
    private final ArrayList<ChatClient> allClients;
    private final List<Message> messages = new LinkedList<>();


    public ConcreteChatServer() throws RemoteException
    {
        allClients = new ArrayList<>();
    }


    @Override
    public synchronized boolean addClient(ChatClient chatClient) throws RemoteException
    {
        String name = chatClient.getNickname();

        for (Iterator<ChatClient> iter = allClients.iterator(); iter.hasNext(); )
        {
            ChatClient cc = iter.next();
            try
            {
                if (cc.getNickname().equals(name))
                {
                    return false;
                }
            }
            catch (RemoteException exc)
            {
                iter.remove();
            }
        }
        allClients.add(chatClient);

        for (Message message : messages)
        {
            chatClient.print(message.getNickName() + ": " + message.getMessage());
        }

        sendMessage(chatClient.getNickname(), "Ich bin dem Chat hinzugestoßen!");

        return true;
    }


    @Override
    public synchronized void removeClient(ChatClient chatClient) throws RemoteException
    {
        sendMessage(chatClient.getNickname(), "Ich gehe nun, Tschüss!");
        allClients.remove(chatClient);
    }


    @Override
    public synchronized void sendMessage(String senderName, String message) throws RemoteException
    {
        messages.add(new Message(senderName, message));
        for (Iterator<ChatClient> iter = allClients.iterator(); iter.hasNext(); )
        {
            ChatClient cc = iter.next();
            try
            {
                cc.print(senderName + ": " + message);
            }
            catch (RemoteException exc)
            {
                iter.remove();
            }
        }
    }
}

