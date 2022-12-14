package servlets.registration;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private ArrayList<Person> people;

    @Override
    public void init()
    {
        ServletContext ctx = getServletContext();
        synchronized (ctx)
        {
            people = (ArrayList<Person>) ctx.getAttribute("personenliste");

            if (people == null)
            {
                people = new ArrayList<Person>();
                ctx.setAttribute("personenliste", people);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        synchronized (people)
        {
            response.setContentType("text/html");
            response.getWriter().append("<html>").append("<head>").append("<title>Angemeldete Benutzer</title>")
                    .append("</head>").append("<body>").append("<h1>Angemeldete Benutzer</h1><ul>");

            for (Person person : people)
            {
                response.getWriter().append("<li>").append(person.getUsername()).append("</li>");
            }

            response.getWriter().append("</li></body>").append("</html>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}