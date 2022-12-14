package com.marvin_elsen.eva.uebung_09.aufgabe_02.sensor;


import java.util.Random;


public class Sensor
{
    private final Random random = new Random(System.currentTimeMillis());

    private final int intervalStart;
    private final int intervalEnd;


    public Sensor(int intervalStart, int intervalEnd)
    {
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
    }


    public int getValue()
    {
        return intervalStart + random.nextInt(intervalEnd - intervalStart);
    }
}
