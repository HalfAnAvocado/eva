package com.marvin_elsen.eva.uebung_08.aufgabe_01.data;


import java.io.IOException;


public interface Counter
{
    int reset() throws IOException;

    int increment() throws IOException;
}
