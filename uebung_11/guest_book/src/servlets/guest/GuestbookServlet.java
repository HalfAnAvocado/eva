package com.marvin_elsen.eva.uebung_11.guest_book.src.servlets.guest;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GuestbookServlet")
public class GuestbookServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private List<GuestBookEntry> entries = new LinkedList<>();


    private String createForm()
    {
        StringBuilder form = new StringBuilder();

        form.append("<form method=\"post\" action=\"GuestbookServlet\">")
                .append("<p>Vorname<input type=\"text\" name=\"vorname\" size=\"20\"/></p>")
                .append("<p>Nachname<input type=\"text\" name=\"nachname\" size=\"20\"/></p>")
                .append("<p>Wohnort<input type=\"text\" name=\"wohnort\" size=\"20\"/></p>")
                .append("<p>Alter<input type=\"number\" name=\"alter\" size=\"20\"/></p>")
                .append("<p><textarea name=\"inhalt\" rows=\"10\" cols=\"70\"></textarea></p>")
                .append("<p><input type=\"submit\" value=\"Beitrag posten\"/></p>").append("</form>");

        return form.toString();
    }


    private synchronized String createEntries()
    {
        StringBuilder entriesHtml = new StringBuilder();

        for (GuestBookEntry entry : entries)
        {
            entriesHtml.append("<div>").append("<p>Vorname:" + entry.getFirstName() + "</p>")
                    .append("<p>Nachname:" + entry.getLastName() + "</p>")
                    .append("<p>Wohnort:" + entry.getLocation() + "</p>").append("<p>Alter:" + entry.getAge() + "</p>")
                    .append("<p>Inhalt: " + entry.getText() + "</p>").append("</div");
        }

        return entriesHtml.toString();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Gästebuch</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Gästebuch</h1>");
        out.println(createForm());
        out.println(createEntries());
        out.println("</body>");
        out.println("</html>");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String firstName = request.getParameter("vorname");
        String lastName = request.getParameter("nachname");
        String location = request.getParameter("wohnort");
        int age = Integer.parseInt(request.getParameter("alter"));
        String text = request.getParameter("inhalt");

        synchronized (entries)
        {
            entries.add(new GuestBookEntry(firstName, lastName, location, age, text));
        }

        doGet(request, response);
    }
}
