package socket.udp.counter;


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
                String request = udpSocket.receive(20);
                if (request.equalsIgnoreCase("increment"))
                {
                    counter++;
                    /*System.out.println("counter incremented by "
                                               + udpSocket.getReplyAddress()
                                               + ":"
                                               + udpSocket.getReplyPort());*/
                }
                else if (request.equalsIgnoreCase("reset"))
                {
                    counter = 0;
                    System.out.println("counter reset by "
                            + udpSocket.getReplyAddress()
                            + ":"
                            + udpSocket.getReplyPort());
                }
                else if (request.equalsIgnoreCase("decrement"))
                {
                    counter--;
                }
                else if (request.startsWith("set"))
                {
                    try
                    {
                        int number = Integer.parseInt(request.substring(request.indexOf(' ') + 1));

                        counter = number;
                    }
                    catch (NumberFormatException e)
                    {
                        System.err.println(e.getMessage());
                    }
                }
                String answer = String.valueOf(counter);
                udpSocket.reply(answer);
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
