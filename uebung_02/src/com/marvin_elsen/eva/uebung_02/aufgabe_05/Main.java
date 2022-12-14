package com.marvin_elsen.eva.uebung_02.aufgabe_05;


import com.marvin_elsen.eva.uebung_02.aufgabe_02.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

// Exception bei Zyklus
// Entfernen von Methoden funktioniert
// Hinzufügen von Methoden und Feldern funktioniert ebenfalls
// StackOverflow bei zu tiefer Verschachtelung


public class Main
{
    public static void main(String[] args) throws FileNotFoundException, JAXBException
    {
        JAXBContext contextObj = JAXBContext.newInstance(Node.class);

        final int valuesCount = 10_000;

        Node rootNode = new Node(1);
        Node lastNode = rootNode;

        for (int i = 2; i <= valuesCount + 1; i++)
        {
            Node newNode = new Node(i);
            lastNode.setNextNode(newNode);
            lastNode = newNode;
        }

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerObj.marshal(rootNode, new FileOutputStream("nodes.xml"));

/*

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        ArrayList<Answer> list = new ArrayList<>();

        Question que  = new Question(1, "What is java?", list);
        Answer   ans1 = new Answer(101, "java is rootNode programming language", new Participant("Petra", 17));
        Answer   ans2 = new Answer(102, "java is rootNode platform", new Participant("Joe", 20));

        list.add(ans1);
        list.add(ans2);

        marshallerObj.marshal(que, new FileOutputStream("question.xml"));*/





/*        try
        {
            Unmarshaller jaxbUnmarshaller = contextObj.createUnmarshaller();
            File         file             = new File("question.xml");

            Question newQuestion = (Question) jaxbUnmarshaller.unmarshal(file);

            System.out.println(newQuestion.getId() + " " + newQuestion.getQuestionName());
            System.out.println("Answers:");

            List<Answer> newList = newQuestion.getAnswers();
            for (Answer ans : newList)
                System.out.println(ans.getId() + " " + ans.getAnswerName() + " " + ans.getTest());
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }*/
    }
}
