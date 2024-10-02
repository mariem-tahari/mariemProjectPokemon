package com.aled;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AfficheNombre", urlPatterns = "/affichenombre")

public class AfficheNombre extends  HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>Compte par 4</TITLE></HEAD>\n" +
                "<BODY>\n" +
                "<center>Compte par 4</center>\n" +
                "<br>");
        for (int i = 0; i < 21; i++) {
            if (i % 4 != 0) {
                out.println("<center>" + i + "</center>\n");
            }
        }
        out.println("</BODY>\n" +
                "</HTML>");

        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
