package com.marvin_elsen.eva.uebung_02.aufgabe_06;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class Main
{
    public static void main(String[] args) throws IOException
    {
        if (args.length < 1)
        {
            throw new IllegalArgumentException("args: <directory>");
        }

        String startDirectoryPath = args[0];

        FileWalker.walkFileTree(Paths.get(startDirectoryPath), new FileVisitor<Path>()
        {
            final StringBuilder stringBuilder = new StringBuilder();
            private int depth = 0;


            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
            {
                System.out.println(stringBuilder.toString() + dir.toAbsolutePath());

                depth++;
                stringBuilder.append('|');
                stringBuilder.append('\t');

                return FileVisitResult.CONTINUE;
            }


            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
            {
                System.out.println(stringBuilder.toString() + file.toAbsolutePath());

                if (file.getFileName().toString().startsWith("~"))
                {
                    System.out.print("Möchten Sie die Datei \"" + file.getFileName() + "\" entfernen? ");
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
                    {
                        if ("ja".equalsIgnoreCase(reader.readLine()))
                        {
                            Files.delete(file);
                        }
                    }
                }

                return FileVisitResult.CONTINUE;
            }


            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
            {
                return FileVisitResult.CONTINUE;
            }


            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
            {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);

                depth--;

                return FileVisitResult.CONTINUE;
            }
        });
    }
}
