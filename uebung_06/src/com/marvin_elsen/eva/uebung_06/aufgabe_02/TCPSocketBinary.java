package com.marvin_elsen.eva.uebung_06.aufgabe_02;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


public class TCPSocketBinary implements AutoCloseable
{
    private final Socket socket;
    private BufferedInputStream istream;
    private BufferedOutputStream ostream;


    public TCPSocketBinary(String serverAddress, int serverPort) throws IOException
    {
        socket = new Socket(serverAddress, serverPort);
        initializeStreams();
    }


    public TCPSocketBinary(Socket socket) throws IOException
    {
        this.socket = socket;
        initializeStreams();
    }


    public void send(byte[] message) throws IOException
    {
        ostream.write(message);
        ostream.flush();
    }


    public byte[] receive() throws IOException
    {
        byte[] buffer = new byte[100];
        int byteCount;
        if ((byteCount = istream.read(buffer)) == -1)
        {
            return null;
        }
        else
        {
            byte[] returnBuffer = new byte[byteCount];
            System.arraycopy(buffer, 0, returnBuffer, 0, byteCount);

            return returnBuffer;
        }
    }


    @Override
    public void close() throws IOException
    {
        socket.close();
    }


    private void initializeStreams() throws IOException
    {
        ostream = new BufferedOutputStream(socket.getOutputStream());
        istream = new BufferedInputStream(socket.getInputStream());
    }
}
