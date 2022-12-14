package servlets.wiki;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WikiServlet")
public class WikiServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private Entry entry;

    @Override
    public void init()
    {
        ServletContext ctx = getServletContext();
        synchronized (ctx)
        {
            entry = (Entry) ctx.getAttribute("entry");

            if (entry == null)
            {
                entry = new Entry();
                ctx.setAttribute("entry", entry);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            response.setContentType("text/html");
            Writer out = response.getWriter();

            out.append("<html>").append("<body>").append("<form method=\"post\">")
                    .append("<textarea name=\"inhalt\" rows=\"5\" cols=\"70\">").append(entry.getText())
                    .append("</textarea>").append("<input type=\"submit\" value=\"Ändern\">").append("</form>")
                    .append("</body>").append("</html>");

            session.setMaxInactiveInterval(10);
            entry.lock(session.getId());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        entry.setText(request.getParameter("inhalt"));

        doGet(request, response);
    }
}