package socket.tcp.mail;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class ReceiveMail
{
    public static void main(String[] args) throws UnknownHostException
    {
        if (args.length != 5)
        {
            throw new IllegalArgumentException();
        }

        InetAddress mailServerAddress = InetAddress.getByName(args[0]);
        int mailServerPort = Integer.parseInt(args[1]);
        int countOfMails = Integer.parseInt(args[2]);
        String loginName = args[3];
        String loginPassword = args[4];

        try (Socket socket = new Socket(mailServerAddress, mailServerPort))
        {
            socket.setSoTimeout(100);
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(reader.readLine());

            sendCommand(writer, reader, "USER " + loginName);
            sendCommand(writer, reader, "PASS " + loginPassword);

            for (int i = 1; i <= countOfMails; i++)
            {
                writer.write("RETR " + i + "\r\n");
                writer.flush();

                StringBuilder message = new StringBuilder();
                String received = reader.readLine();
                while (!received.equals("."))
                {
                    message.append(received).append(System.lineSeparator());
                    received = reader.readLine();
                }
                message.append(received);

                System.out.println(message);
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
