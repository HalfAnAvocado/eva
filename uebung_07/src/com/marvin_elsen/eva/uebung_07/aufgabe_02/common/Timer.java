package com.marvin_elsen.eva.uebung_07.aufgabe_02.common;


public class Timer
{
    private long startTime = System.currentTimeMillis();
    private long endTime;
    private boolean isStopped;


    public void stop()
    {
        isStopped = true;
        endTime = System.currentTimeMillis();
    }


    public long getElapsed()
    {
        if (isStopped)
        {
            return endTime - startTime;
        }
        else
        {
            return System.currentTimeMillis() - startTime;
        }
    }


    public void reset()
    {
        isStopped = false;
        startTime = System.currentTimeMillis();
        endTime = 0;
    }
}
