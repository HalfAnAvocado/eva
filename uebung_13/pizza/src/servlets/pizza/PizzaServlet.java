package servlets.pizza;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PizzaServlet")
public class PizzaServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private Writer writer;
    private String title = "Marvin's Pizza Parlor";
    
    private Map<String, Order> orderMap = new HashMap<>();
    
    private void writeHead() throws IOException
    {
        writer
        .append("<head>")
        .append("<title>")
        .append(title)
        .append("</title>")
        .append("</head>");
    }
    
    private void writeBodyGet(String kundenname, String kundennummer, String lieferadresse, String pizza) throws IOException
    {
        writer
        .append("<body>")
        .append("<h1>")
        .append(title)
        .append("</h1>")
        .append("<h2>")
        .append("Bestellung tätigen")
        .append("</h2>");
        writeForm(kundenname, kundennummer, lieferadresse, pizza);
        writer.append("</body>");
    }
    
    private void writeBodyPost(String kundenname, String kundennummer, String lieferadresse, String pizza) throws IOException
    {
        writer
        .append("<body>")
        .append("<h1>")
        .append(title)
        .append("</h1>")
        .append("<h2>")
        .append("Bestellbestätigung")
        .append("</h2>")
        .append("<p>Kundenname: ")
        .append(kundenname)
        .append("</p>")
        .append("<p>Kundennummer: ")
        .append(kundennummer)
        .append("</p>")
        .append("<p>Lieferadresse: ")
        .append(lieferadresse)
        .append("</p>")
        .append("<p>Pizza: ")
        .append(pizza)
        .append("</p>");
        writer.append("</body>");
    }
    
    private void writeForm(String kundenname, String kundennummer, String lieferadresse, String pizza) throws IOException
    {
        writer
        .append("<form method=\"post\">")
        .append("<label for=\"kundenname\">Kundenname</label>")
        .append("<input type=\"text\" name=\"kundenname\" id=\"kundenname\" value=\"").append(kundenname).append("\">")
        .append("<br>")
        .append("<label for=\"kundennummer\">Kundennummer</label>")
        .append("<input type=\"text\" name=\"kundennummer\" id=\"kundennummer\" value=\"").append(kundennummer).append("\">")
        .append("<br>")
        .append("<label for=\"adresse\">Lieferadresse</label>")
        .append("<textarea name=\"adresse\" id=\"adresse\">").append(lieferadresse).append("</textarea>")
        .append("<br>")
        .append("<label for=\"pizzaliste\">Pizzaliste</label>")
        
        .append("<select name=\"pizzaliste\">")
        .append("<option value=\"Vegetaria\" ");
        if (pizza.equalsIgnoreCase("Vegetaria"))
        {
            writer.append("selected");
        }
        writer.append(">Vegetaria").append("</option>")
        
        .append("<option value=\"Diavolo\" ");
        if (pizza.equalsIgnoreCase("Diavolo"))
        {
            writer.append("selected");
        }
        writer.append(">Diavolo").append("</option>")
        
        .append("<option value=\"Quattro Stagioni\" ");
        if (pizza.equalsIgnoreCase("Quattro Stagioni"))
        {
            writer.append("selected");
        }
        writer.append(">Quattro Stagioni").append("</option>")
        .append("</select>")
        .append("<br>")
        .append("<input type=\"submit\" value=\"Bestellen\">")
        .append("</form>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {        
        String kundenname = "";
        String kundennummer = "";
        String lieferadresse = "";
        String pizza = "";
        
        if (request.getCookies() != null && request.getCookies().length == 1)
        {
            Cookie cookie = request.getCookies()[0];
            Order order = orderMap.get(cookie.getValue());
            kundenname = order.getKundenname();
            kundennummer = order.getKundennummer();
            lieferadresse = order.getLieferadresse();
            pizza = order.getPizza();
        }
        
        response.setContentType("text/html");
        writer = response.getWriter();
        
        writer.append("<html>");
        writeHead();
        writeBodyGet(kundenname, kundennummer, lieferadresse, pizza);
        writer.append("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        writer = response.getWriter();
        
        String kundenname = request.getParameter("kundenname");
        String kundennummer = request.getParameter("kundennummer");
        String lieferadresse = request.getParameter("adresse");
        String pizza = request.getParameter("pizzaliste");
        
        Cookie cookie = new Cookie("pizzaCookie", kundennummer);
        cookie.setMaxAge(10);
        response.addCookie(cookie);
        orderMap.put(kundennummer, new Order(kundenname, kundennummer, lieferadresse, pizza));
        
        writer.append("<html>");
        writeHead();
        writeBodyPost(kundenname, kundennummer, lieferadresse, pizza);
        writer.append("</html>");
    }
}
