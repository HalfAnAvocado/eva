package com.marvin_elsen.eva.uebung_11.guest_book.src.servlets.guest;


public class GuestBookEntry
{
    private String firstName;
    private String lastName;

    private String location;

    private int age;

    private String text;


    public GuestBookEntry(String firstName, String lastName, String location, int age, String text)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.age = age;
        this.text = text;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


    public String getLocation()
    {
        return location;
    }


    public int getAge()
    {
        return age;
    }


    public String getText()
    {
        return text;
    }
}
