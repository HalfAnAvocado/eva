package com.marvin_elsen.eva.uebung_03.aufgabe_04;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class UDPSocket implements AutoCloseable
{
    protected DatagramSocket socket;
    private InetAddress replyAddress;
    private int replyPort;


    public UDPSocket() throws SocketException
    {
        this(new DatagramSocket());
    }


    public UDPSocket(int port) throws SocketException
    {
        this(new DatagramSocket(port));
    }


    public UDPSocket(DatagramSocket socket)
    {
        this.socket = socket;
    }


    public void send(byte[] message, InetAddress destinationAddress, int destinationPort) throws IOException
    {
        DatagramPacket datagramPacket =
                new DatagramPacket(message, message.length, destinationAddress, destinationPort);

        socket.send(datagramPacket);
    }


    public byte[] receive(int maxBytes) throws IOException
    {
        byte[] buffer = new byte[maxBytes];
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

        socket.receive(datagramPacket);
        replyAddress = datagramPacket.getAddress();
        replyPort = datagramPacket.getPort();

        return buffer;
    }


    public void reply(byte[] message) throws IOException
    {
        if (replyAddress == null)
        {
            throw new IOException("No one to reply to");
        }

        send(message, replyAddress, replyPort);
    }


    public InetAddress getReplyAddress()
    {
        return replyAddress;
    }


    public int getReplyPort()
    {
        return replyPort;
    }


    public void setTimeOut(int milliSeconds) throws SocketException
    {
        socket.setSoTimeout(milliSeconds);
    }


    @Override
    public void close() throws Exception
    {
        socket.close();
    }
}
