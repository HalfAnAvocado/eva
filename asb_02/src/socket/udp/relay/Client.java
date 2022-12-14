package socket.udp.relay;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static socket.udp.relay.UDPRelay.BUFFER_SIZE;


public class Client extends Thread
{
    private final DatagramSocket inSocket;
    private final DatagramPacket inPacket;
    private final Reader controlReader;
    private final InetAddress addressToRelayTo;
    private final int portToRelayTo;


    public Client(DatagramSocket inSocket, DatagramPacket inPacket, Reader controlReader,
                  InetAddress addressToRelayTo, int portToRelayTo)
    {
        this.inSocket = inSocket;
        this.inPacket = inPacket;
        this.controlReader = controlReader;
        this.addressToRelayTo = addressToRelayTo;
        this.portToRelayTo = portToRelayTo;
    }


    public static boolean askUserDecision(Reader reader, DatagramPacket packet) throws IOException
    {
        System.out.format("Paket von [%s:%s] erhalten. Möchten Sie das Paket weiterleiten? ",
                packet.getAddress().getHostAddress(), packet.getPort());

        reader = new BufferedReader(reader);

        return ((BufferedReader) reader).readLine().equalsIgnoreCase("j");
    }


    @Override
    public void run()
    {
        try (DatagramSocket outSocket = new DatagramSocket())
        {
            DatagramPacket outPacket =
                    new DatagramPacket(inPacket.getData(), inPacket.getLength(),
                            addressToRelayTo,
                            portToRelayTo);
            outSocket.send(outPacket);
            System.out.println("Paket weitergeleitet");

            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(buffer, BUFFER_SIZE);
            outSocket.receive(receivePacket);

            if (askUserDecision(controlReader, receivePacket))
            {
                DatagramPacket sendPacket = new DatagramPacket(buffer,
                        receivePacket.getLength(),
                        inPacket.getAddress(),
                        inPacket.getPort());
                inSocket.send(sendPacket);
                System.out.println("Paket weitergeleitet");
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
