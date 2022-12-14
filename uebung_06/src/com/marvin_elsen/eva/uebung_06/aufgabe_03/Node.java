package com.marvin_elsen.eva.uebung_06.aufgabe_03;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class Node implements Serializable
{
    private final List<Node> children = new LinkedList<>();
    private int value;


    public Node(int value)
    {
        this.value = value;
    }


    public void addChild(Node node)
    {
        children.add(node);
    }


    public int getValue()
    {
        return value;
    }


    public void setValue(int value)
    {
        this.value = value;
    }


    // https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
    public void print()
    {
        print("", true);
    }


    private void print(String prefix, boolean isTail)
    {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + value);
        for (int i = 0; i < children.size() - 1; i++)
        {
            children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0)
        {
            children.get(children.size() - 1)
                    .print(prefix + (isTail ? "    " : "│   "), true);
        }
    }


    @Override
    public String toString()
    {
        return "(" + value + ")";
    }
}
