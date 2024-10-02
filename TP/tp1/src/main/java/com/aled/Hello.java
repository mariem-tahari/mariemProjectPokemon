package com.aled;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Hello", urlPatterns = "/helloservlet")
public class Hello extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>Hello Servlet</TITLE></HEAD>\n" +
                "<BODY>\n" +
                "<center>HELLO SERVLET</center>\n" +
                "</BODY>\n" +
                "</HTML>");
        out.flush();
        out.close();
    }


    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
