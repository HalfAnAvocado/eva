package com.marvin_elsen.eva.uebung_01.aufgabe_06;


import java.io.IOException;
import java.io.OutputStream;


public class MultiplierOutputStream extends OutputStream
{
    private final OutputStream[] outputStreams;


    MultiplierOutputStream(OutputStream... outputStreams)
    {
        this.outputStreams = outputStreams;
    }


    @Override
    public void write(int b) throws IOException
    {
        for (var outputStream : outputStreams)
            outputStream.write(b);
    }


    @Override
    public void write(byte[] b) throws IOException
    {
        for (var outputStream : outputStreams)
            outputStream.write(b);
    }


    @Override
    public void write(byte[] b, int off, int len) throws IOException
    {
        for (var outputStream : outputStreams)
            outputStream.write(b, off, len);
    }


    @Override
    public void flush() throws IOException
    {
        for (var outputStream : outputStreams)
            outputStream.flush();

        super.flush();
    }


    @Override
    public void close() throws IOException
    {
        for (var outputStream : outputStreams)
            outputStream.close();

        super.close();
    }
}
