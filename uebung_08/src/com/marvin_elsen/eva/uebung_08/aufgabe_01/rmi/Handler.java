package com.marvin_elsen.eva.uebung_08.aufgabe_01.rmi;


import com.marvin_elsen.eva.uebung_08.aufgabe_01.data.Counter;

import java.io.*;
import java.net.Socket;
import java.util.Map;

import static com.marvin_elsen.eva.uebung_08.aufgabe_01.rmi.Protocol.INCREMENT;
import static com.marvin_elsen.eva.uebung_08.aufgabe_01.rmi.Protocol.RESET;


class Handler implements Runnable
{
    private final Socket socket;
    private final Map<String, Counter> counters;


    Handler(Socket socket, Map<String, Counter> counters)
    {
        this.socket = socket;
        this.counters = counters;
    }


    @Override
    public void run()
    {
        try (socket;
             var outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
             var inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream())))
        {
            var counterId = inputStream.readUTF();
            var counter = counters.get(counterId);
            while (true)
            {
                try
                {
                    var command = inputStream.readByte();

                    if (command == RESET)
                    {
                        outputStream.writeInt(counter.reset());
                    }
                    else if (command == INCREMENT)
                    {
                        outputStream.writeInt(counter.increment());
                    }

                    outputStream.flush();
                }
                catch (IOException e)
                {
                    break;
                }
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
