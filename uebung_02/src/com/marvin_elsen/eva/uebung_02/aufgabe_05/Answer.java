package com.marvin_elsen.eva.uebung_02.aufgabe_05;


import java.io.Serializable;


public class Answer implements Serializable
{
    private int id;
    private String answerName;
    private Participant participant;
    private String test;
    // private Question question;


    public Answer()
    {

    }


    public Answer(int id, String answerName,
                  Participant participant)
    {
        this.id = id;
        this.answerName = answerName;
        this.participant = participant;
    }


    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id = id;
    }


    public String getAnswerName()
    {
        return answerName;
    }


    public void setAnswerName(String answerName)
    {
        this.answerName = answerName;
    }


    public Participant getParticipant()
    {
        return participant;
    }


    public void setParticipant(Participant participant)
    {
        this.participant = participant;
    }


    public String getTest()
    {
        return test;
    }


    public void setTest(String test)
    {
        this.test = test;
    }

    /*    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }*/
}
