package com.marvin_elsen.eva.uebung_10.aufgabe_01;


public class AgentRunner implements Runnable
{
    private final String heartbeat;


    public AgentRunner(String heartbeat)
    {
        this.heartbeat = heartbeat;
    }


    @Override
    public void run()
    {
        try
        {
            while (!Thread.interrupted()) // oder Thread.currentThread().isInterrupted()
            {
                Thread.sleep(1000);
                System.out.println(heartbeat);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("- Thread end -");
        }
    }
}
