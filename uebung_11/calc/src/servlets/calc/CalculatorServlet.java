package com.marvin_elsen.eva.uebung_11.calc.src.servlets.calc;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        long firstOp = Long.parseLong(request.getParameter("firstOp"));
        long secondOp = Long.parseLong(request.getParameter("secondOp"));
        long result = 0;
        String operation = request.getParameter("operation");

        switch (operation)
        {
            case "+":
                result = firstOp + secondOp;
                break;

            case "-":
                result = firstOp - secondOp;
                break;

            case "*":
                result = firstOp * secondOp;
                break;

            case "/":
                result = firstOp / secondOp;
                break;

            default:
                break;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Ergebnis</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Ergebnis</h1>");
        out.println("<p id=\"result\">" + Long.toString(result) + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
