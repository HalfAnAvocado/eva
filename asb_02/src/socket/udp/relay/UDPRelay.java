package socket.udp.relay;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;

import static socket.udp.relay.Client.askUserDecision;


// Problem: Wenn Server mehrere Antworten schickt, gehen alle außer die erste verloren
// Außerdem: Wenn Server nicht antwortet oder Antwort verloren geht, Ressourcen verschwendet
// und eventuell werden nach Monaten alle Ports blockiert.
public class UDPRelay
{
    public static final int BUFFER_SIZE = 100;


    public static void runUDPRelay(DatagramSocket inSocket, InetAddress addressToRelayTo,
                                   int portToRelayTo, Reader controlReader)
    {
        while (true)
        {
            try
            {
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket inPacket = new DatagramPacket(buffer, BUFFER_SIZE);

                inSocket.receive(inPacket);

                if (askUserDecision(controlReader, inPacket))
                {
                    Client client = new Client(inSocket, inPacket, controlReader, addressToRelayTo,
                            portToRelayTo);
                    client.start();
                }
                else
                {
                    System.out.println("Paket verworfen");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws UnknownHostException, SocketException
    {
        if (args.length != 3)
        {
            throw new IllegalArgumentException("Falsche Argumente!");
        }

        int inPort = Integer.parseInt(args[0]);
        InetAddress addressToRelayTo = InetAddress.getByName(args[1]);
        int portToRelayTo = Integer.parseInt(args[2]);

        Reader controlReader = new InputStreamReader(System.in);

        try (DatagramSocket socket = new DatagramSocket(inPort))
        {
            UDPRelay.runUDPRelay(socket, addressToRelayTo, portToRelayTo, controlReader);
        }
    }
}
