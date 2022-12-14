package com.marvin_elsen.eva.uebung_09.aufgabe_02.sensor;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;


public class Main
{
    private static final int SECONDS_TO_MS = 1000;


    public static void main(String[] args) throws UnknownHostException
    {
        if (args.length != 5)
        {
            throw new IllegalArgumentException();
        }

        var serverAddress = InetAddress.getByName(args[0]);
        var serverPort = Integer.parseInt(args[1]);
        var updateIntervalInMs = Integer.parseInt(args[2]) * SECONDS_TO_MS;

        var sensor = new Sensor(Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        try (var socket = new DatagramSocket())
        {
            var isRunning = true;
            while (isRunning)
            {
                try
                {
                    Thread.sleep(updateIntervalInMs);
                    sendSensorValue(socket, serverAddress, serverPort, sensor.getValue());
                }
                catch (InterruptedException | IOException e)
                {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        catch (SocketException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }


    private static void sendSensorValue(DatagramSocket socket, InetAddress serverAddress, int serverPort, int sensorValue)
            throws IOException
    {
        try (var byteArrayOutputStream = new ByteArrayOutputStream(Integer.BYTES);
             var dataOutputStream = new DataOutputStream(byteArrayOutputStream))
        {
            dataOutputStream.writeInt(sensorValue);

            var packet = new DatagramPacket(byteArrayOutputStream.toByteArray(), 0, Integer.BYTES, serverAddress, serverPort);
            socket.send(packet);
        }
    }
}
