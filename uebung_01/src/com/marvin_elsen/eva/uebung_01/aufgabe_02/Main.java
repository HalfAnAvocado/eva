package com.marvin_elsen.eva.uebung_01.aufgabe_02;


import java.time.LocalDateTime;
import java.util.Locale;


public class Main
{
    public static void main(String[] args)
    {
        // https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax
        //  %[argument_index$][flags][width][.precision]conversion
        System.out.format("Wert: %+5d\nHex: 0x%1$08X\n", -255);

        System.out.format("Wert: %.2f\n", 255.245);
        System.out.format(Locale.GERMANY, "Wert: %.2f\n", 255.245);

        LocalDateTime now = LocalDateTime.now();
        String formattedString = "Momentane Uhrzeit und Datum: %tT - %1$tA der %1$te. %1$tB %1$tY\n";

        // Mit Locale.getDefault()
        System.out.format(formattedString, now);

        System.out.format(Locale.TAIWAN, formattedString, now);
    }
}
