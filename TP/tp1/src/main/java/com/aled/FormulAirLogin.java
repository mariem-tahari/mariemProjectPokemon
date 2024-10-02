package com.aled;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "FormulAirLogin", urlPatterns = "/login")
public class FormulAirLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//FR\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>FormulAir Login</TITLE></HEAD>\n" +
                "<BODY>\n" +
                "<center>FormulAir Login</center>\n" +
                "<br>");

        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");

        if (FormuleAir.users.containsKey(mail) && FormuleAir.users.get(mail).equals(pass)) {
            out.println("Welcome " + mail + "<br>");
            resp.sendRedirect("/annuaire");
        } else {
            out.println("Wrong credentials <br>");
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
