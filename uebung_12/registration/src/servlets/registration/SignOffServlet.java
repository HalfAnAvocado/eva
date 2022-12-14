package servlets.registration;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignOffServlet")
public class SignOffServlet extends HttpServlet
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
        response.setContentType("text/html");
        response.getWriter().append("<html>").append("<head>").append("<title>Abmelden</title>").append("</head>")
                .append("<body>").append("<h1>Abmelden</h1>").append("<form method=\"post\">")
                .append("Benutzername: <input type=\"text\" name=\"benutzername\" size=\"40\"><br>")
                .append("Passwort: <input type=\"password\" name=\"passwort\" size=\"40\"><br>")
                .append("<input type=\"submit\" value=\"Abmelden\">").append("</form>").append("</body>")
                .append("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        Writer out = response.getWriter();
        out.append("<html>").append("<head>");
        
        String username = request.getParameter("benutzername");
        String password = request.getParameter("passwort");
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty())
        {
            synchronized (people)
            {
                Person person = null;

                for (Person tempPerson : people)
                {
                    if (tempPerson.getUsername().equalsIgnoreCase(username) && tempPerson.isPasswordValid(password))
                    {
                        person = tempPerson;
                    }
                }

                if (person != null)
                {
                    people.remove(person);
                    
                    out.append("<title>Erfolgreich</title>").append("</head>")
                    .append("<body>").append("<h1>Erfolgreich abgemeldet!</h1>").append("</body>")
                    .append("</html>");
                }
                else
                {
                    out.append("<title>Fehler</title>").append("</head>")
                    .append("<body>").append("<h1>Benutzername oder Passwort falsch!</h1>").append("</body>")
                    .append("</html>");
                }
            }
        }
    }
}
