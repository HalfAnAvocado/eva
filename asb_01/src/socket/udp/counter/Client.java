package socket.udp.counter;


import java.net.InetAddress;


public class Client
{
    private static final int timeout = 2000;


    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            throw new IllegalArgumentException("ARGS: <ADDRESS> <PORT> <COUNT>");
        }

        try (UDPSocket udpSocket = new UDPSocket())
        {
            udpSocket.setTimeOut(timeout);

            InetAddress serverAddr = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);

            System.out.println("setting counter to 0");
            udpSocket.send("reset", serverAddr, port);

            String reply = null;
            try
            {
                reply = udpSocket.receive(20);
                System.out.println("counter = " + reply);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

            System.out.println("incrementing");
            int count = Integer.valueOf(args[2]);
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < count; i++)
            {
                udpSocket.send("increment", serverAddr, port);
                try
                {
                    reply = udpSocket.receive(20);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }

            long stopTime = System.currentTimeMillis();
            long duration = stopTime - startTime;

            System.out.println("elapsed time = " + duration + " msecs");

            if (count > 0)
            {
                System.out.println("average ping = " + ((duration) / (float) count) + " msecs");
            }


            System.out.println("counter = " + reply);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("UDP socket closed");
    }
}
