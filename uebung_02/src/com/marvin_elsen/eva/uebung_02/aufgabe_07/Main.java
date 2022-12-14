package com.marvin_elsen.eva.uebung_02.aufgabe_07;


import java.io.IOException;
import java.nio.file.*;


public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        if (args.length < 1)
        {
            throw new IllegalArgumentException("args: <directory>");
        }

        String directoryPath = args[0];

        WatchService watchService = FileSystems.getDefault().newWatchService();
        Paths.get(directoryPath)
                .register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                        StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null)
        {
            for (WatchEvent event : key.pollEvents())
                System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");

            key.reset();
        }
    }
}