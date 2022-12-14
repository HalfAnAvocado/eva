package com.marvin_elsen.eva.uebung_08.aufgabe_01.rmi;


import com.marvin_elsen.eva.uebung_08.aufgabe_01.data.Counter;

import java.io.*;
import java.net.Socket;

import static com.marvin_elsen.eva.uebung_08.aufgabe_01.rmi.Protocol.INCREMENT;
import static com.marvin_elsen.eva.uebung_08.aufgabe_01.rmi.Protocol.RESET;


public class CounterStub implements Counter
{
    private final Socket socket;
    private final DataOutputStream outputStream;
    private final DataInputStream inputStream;

    private final String counterId;


    public CounterStub(String hostname, int port, String counterId) throws IOException
    {
        socket = new Socket(hostname, port);
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        this.counterId = counterId;

        outputStream.writeUTF(counterId);
        outputStream.flush();
    }


    @Override
    public int reset() throws IOException
    {
        outputStream.writeByte(RESET);
        outputStream.flush();

        return inputStream.readInt();
    }


    @Override
    public int increment() throws IOException
    {
        outputStream.writeByte(INCREMENT);
        outputStream.flush();

        return inputStream.readInt();
    }
}
