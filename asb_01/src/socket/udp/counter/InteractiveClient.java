package socket.udp.counter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class InteractiveClient
{
    private static final int TIMEOUT = 10_000;
    private static final int RETRY_ATTEMPTS = 2;
    private static final boolean isRunning = true;


    public static void main(String[] args) throws UnknownHostException
    {
        if (args.length != 2)
        {
            throw new IllegalArgumentException("ARGS: <ADDRESS> <PORT>");
        }

        InetAddress serverAddr = InetAddress.getByName(args[0]);
        int port = Integer.parseInt(args[1]);

        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
             UDPSocket udpSocket = new UDPSocket())
        {
            udpSocket.setTimeOut(TIMEOUT);

            while (isRunning)
            {
                System.out.print("Kommando: ");
                String command = stdIn.readLine();

                if (command.equalsIgnoreCase("exit"))
                {
                    System.exit(0);
                }
                else
                {
                    // Klausurrelevant
                    // Sollte nur fürs Lesen/Abfragen verwendet werden oder beim Schreiben nur
                    // für idempotente Operationen

                    // Bessere Lösung (Siehe Xournal-Datei aus Tutorium):
                    // Schichten:
                    // Anwendung [ASCII]
                    // Reliable UDP (Unsere Lösung) [ASCII]
                    // UDP
                    // IP
                    for (int i = 0; i < RETRY_ATTEMPTS; i++)
                    {
                        try
                        {
                            udpSocket.send(command, serverAddr, port);

                            String reply;

                            reply = udpSocket.receive(20);
                            System.out.println("counter = " + reply);

                            break;
                        }
                        catch (IOException e)
                        {
                            System.err.println(e.getMessage());
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
