package com.marvin_elsen.eva.uebung_11.hello_world.src.servlets.hello;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/HalloWelt")
public class HelloWorldServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hallo Welt!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hallo Welt!</h1>");
        out.println("<p>" + LocalDateTime.now().toString() + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
