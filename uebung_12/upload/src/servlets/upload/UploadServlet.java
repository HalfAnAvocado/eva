package servlets.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        Writer out = response.getWriter();

        out.append("<html>").append("<body>").append("<form method=\"post\" enctype=\"multipart/form-data\">")
                .append("Dateiname: <input name=\"filename\" type=\"text\"><br>")
                .append("Datei: <input name=\"upload\" type=\"file\"><br>")
                .append("<input type=\"submit\" value=\"Jetzt hochladen!\">").append("</form>");

        File dir = new File("/home/elsenm/www");
        
        out.append("<ul>");
        for (File file : dir.listFiles())
        {
            out.append("<li><a href=\"").append(file.getPath()).append("\">").append(file.getName()).append("</a></li>");
        }
        
        out.append("</ul></body>")
        .append("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Part filePart = request.getPart("upload");

        String fileName = filePart.getSubmittedFileName(); 
        InputStream is = filePart.getInputStream();

        Files.copy(is, Paths.get("/home/elsenm/www/" + fileName), StandardCopyOption.REPLACE_EXISTING);
        
        doGet(request, response);
    }

}
