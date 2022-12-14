package socket.tcp.scan;


import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

// Falls eine Firewall gewisse Ports sperrt, so kann das Überprüfen einer einzigen Portnummer
// relativ lange dauern. Warum?
// => Firewall verwirft Pakete einfach, anstatt eine Meldung zurückzugeben.

// Wie können Sie die Laufzeit Ihres Programms für die Überprüfung vieler Ports verringern?
// => Multi-Threading und Port-Bereich auf mehrer Threads aufteilen

// Warum ist so etwas für UDP nicht so einfach durchführbar?
// => Nicht verbindungsorientiert, also keine Information darüber, ob zu einem Port verbunden
// werden kann oder nicht


public class PortScanner
{
    public static void main(String[] args) throws IOException
    {
        if (args.length != 3)
        {
            throw new IllegalArgumentException();
        }

        InetAddress targetAddress = InetAddress.getByName(args[0]);
        int firstPort = Integer.parseInt(args[1]);
        int lastPort = Integer.parseInt(args[2]);

        for (int port = firstPort; port <= lastPort; port++)
        {
            try (Socket socket = new Socket())
            {
                socket.connect(new InetSocketAddress(targetAddress, port), 100);
                System.out.println(port);
            }
            catch (IOException e)
            {
                // ...
            }
        }
    }
}
