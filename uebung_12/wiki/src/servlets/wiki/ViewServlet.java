package servlets.wiki;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet
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
        response.setContentType("text/html");
        Writer out = response.getWriter();

        out.append("<html>").append("<head><title>Eintrag-Lesen></title></head>").append("<body>").append("<p>")
                .append(entry.getText()).append("</p>").append("<form method=\"post\">")
                .append("<input type=\"submit\" value=\"Editieren\">").append("</form>").append("</body>")
                .append("</html>");
        
        HttpSession session = request.getSession(true);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
