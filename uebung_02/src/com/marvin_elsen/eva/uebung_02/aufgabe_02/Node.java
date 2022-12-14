package com.marvin_elsen.eva.uebung_02.aufgabe_02;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
public class Node implements Serializable
{
    private int id;

    private Node nextNode;


    public Node()
    {
    }


    public Node(int id)
    {
        this.id = id;
    }


    public Node(int id, Node nextNode)
    {
        this.id = id;
        this.nextNode = nextNode;
    }


    @XmlAttribute
    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id = id;
    }


    @XmlElement
    public Node getNextNode()
    {
        return nextNode;
    }


    public void setNextNode(Node node)
    {
        nextNode = node;
    }
}
