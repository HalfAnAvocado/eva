package aufgabe_01;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


// Für andere DNS-Records:
// http://www.xbill.org/dnsjava/
// TXT - Text
// MX - Mail Exchange
// CNAME - Canonical Name
// NS - Name Server
public class Main
{
    public static void main(String[] args) throws IOException
    {
        StringBuilder output = new StringBuilder();
        for (var arg : args)
        {
            output.append("---------------------------------------------------------------");
            output.append(System.lineSeparator())
                    .append(arg)
                    .append(':')
                    .append(System.lineSeparator());

            try
            {
                InetAddress[] inetAddresses = InetAddress.getAllByName(arg);
                for (var inetAddress : inetAddresses)
                {
                    output.append(inetAddress.getHostName())
                            .append(" => ")
                            .append(inetAddress.getHostAddress())
                            .append(System.lineSeparator());
                }
                output.append("Erreichbar: ")
                        .append(inetAddresses[0].isReachable(1000))
                        .append(System.lineSeparator());

                System.out.print(output);
            }
            catch (UnknownHostException e)
            {
                System.out.print(output);
                System.err.format("Host %s nicht auflösbar%s", arg, System.lineSeparator());
            }
            finally
            {
                output.delete(0, output.length());
            }
        }
    }
}
