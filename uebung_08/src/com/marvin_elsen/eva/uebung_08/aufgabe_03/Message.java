package com.marvin_elsen.eva.uebung_08.aufgabe_03;


public class Message
{
    private final String nickName;
    private final String message;


    public Message(String nickName, String message)
    {
        this.nickName = nickName;
        this.message = message;
    }


    public String getNickName()
    {
        return nickName;
    }


    public String getMessage()
    {
        return message;
    }
}
