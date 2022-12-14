package com.marvin_elsen.eva.uebung_02.aufgabe_06;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;


public class FileWalker
{
    public static void walkFileTree(Path path, FileVisitor<? super Path> fileVisitor)
            throws IOException
    {
        if (Files.isDirectory(path))
        {
            if (fileVisitor.preVisitDirectory(path,
                    Files.readAttributes(path, BasicFileAttributes.class))
                    == FileVisitResult.CONTINUE)
            {
                for (File file : path.toFile().listFiles())
                    walkFileTree(file.toPath(), fileVisitor);

                fileVisitor.postVisitDirectory(path, null);
            }
            else
            {
                fileVisitor.postVisitDirectory(path, new IOException());
            }
        }
        else
        {
            if (fileVisitor.visitFile(path, Files.readAttributes(path, BasicFileAttributes.class))
                    != FileVisitResult.CONTINUE)
            {
                fileVisitor.visitFileFailed(path, new IOException());
            }
        }
    }
}
