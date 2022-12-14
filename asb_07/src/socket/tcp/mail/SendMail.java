package socket.tcp.mail;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class SendMail
{
    public static void main(String[] args) throws UnknownHostException
    {
        if (args.length != 7)
        {
            throw new IllegalArgumentException();
        }

        InetAddress mailServerAddress = InetAddress.getByName(args[0]);
        int mailServerPort = Integer.parseInt(args[1]);

        String clientIdentifier = args[2];
        String mailAddressSender = args[3];
        String mailAddressRecipient = args[4];
        String mailBody = args[5];
        int sendCount = Integer.parseInt(args[6]);

        try (Socket socket = new Socket(mailServerAddress, mailServerPort))
        {
            socket.setSoTimeout(100);
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(reader.readLine());

            sendCommand(writer, reader, "HELO " + clientIdentifier);

            for (int i = 0; i < sendCount; i++)
            {
                sendCommand(writer, reader, "MAIL FROM:<" + mailAddressSender + ">");
                sendCommand(writer, reader, "RCPT TO:<" + mailAddressRecipient + ">");
                sendCommand(writer, reader, "DATA");

                writer.write(mailBody);
                writer.flush();
                System.out.print(mailBody);

                sendCommand(writer, reader, "\r\n.");
            }
            sendCommand(writer, reader, "QUIT");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    private static void sendCommand(BufferedWriter writer, BufferedReader reader, String command)
            throws IOException
    {
        writer.write(command + "\r\n");
        writer.flush();
        System.out.println("C: " + command);
        System.out.println("S: " + reader.readLine());
    }
}
