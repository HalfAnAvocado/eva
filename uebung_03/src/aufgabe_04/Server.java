package aufgabe_04;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramSocket;
import java.net.SocketException;


public class Server
{
    private static int counter;


    public static void runServer(DatagramSocket socket)
    {
        try (UDPSocket udpSocket = new UDPSocket(socket))
        {
            System.out.println("waiting for client requests");
            while (true)
            {
                byte[] request = udpSocket.receive(100);
                try (DataInputStream inputStream = new DataInputStream(
                        new ByteArrayInputStream(request)))
                {
                    int command = inputStream.readByte();
                    if (command == 1)
                    {
                        counter++;
                    }
                    else if (command == 0)
                    {
                        counter = 0;
                        System.out.println("counter reset by "
                                + udpSocket.getReplyAddress()
                                + ":"
                                + udpSocket.getReplyPort());
                    }
                    else if (command == 2)
                    {
                        counter--;
                    }
                    else if (command == 3)
                    {
                        try
                        {
                            int number = inputStream.readInt();

                            counter = number;
                        }
                        catch (Exception e)
                        {
                            System.err.println(e.getMessage());
                        }
                    }
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
                try (DataOutputStream dataOutputStream = new DataOutputStream(
                        byteArrayOutputStream))
                {
                    dataOutputStream.writeInt(counter);
                    udpSocket.reply(byteArrayOutputStream.toByteArray());
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }

        System.out.println("UDP socket closed");
    }


    public static void main(String[] args) throws SocketException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("ARGS: <LISTEN_PORT>");
        }

        int port = Integer.parseInt(args[0]);
        System.out.println(port);
        try (DatagramSocket socket = new DatagramSocket(port))
        {
            System.out.println(socket.getLocalAddress().getHostAddress());
            System.out.println(socket.getLocalPort());
            runServer(socket);
        }
    }
}
