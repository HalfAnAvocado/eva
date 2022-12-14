package com.marvin_elsen.eva.uebung_06.aufgabe_01;


import java.io.*;
import java.net.Socket;


public class TCPSocket implements AutoCloseable
{
    private final Socket socket;
    private BufferedReader istream;
    private BufferedWriter ostream;


    public TCPSocket(String serverAddress, int serverPort) throws IOException
    {
        socket = new Socket(serverAddress, serverPort);
        initializeStreams();
    }


    public TCPSocket(Socket socket) throws IOException
    {
        this.socket = socket;
        initializeStreams();
    }


    public void sendLine(String s) throws IOException
    {
        ostream.write(s);
        ostream.newLine();
        ostream.flush();
    }


    public void send(String s) throws IOException
    {
        ostream.write(s);
        ostream.flush();
    }


    public String receiveLine() throws IOException
    {
        return istream.readLine();
    }


    public String receive() throws IOException
    {
        char[] buffer = new char[100];
        int charCount;
        if ((charCount = istream.read(buffer)) == -1)
        {
            return null;
        }
        else
        {
            return new String(buffer, 0, charCount);
        }
    }


    @Override
    public void close() throws IOException
    {
        socket.close();
    }


    private void initializeStreams() throws IOException
    {
        ostream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        istream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
