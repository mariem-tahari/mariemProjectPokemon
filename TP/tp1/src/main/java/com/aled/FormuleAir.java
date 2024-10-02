package com.aled;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "FormuleAir", urlPatterns = "/annuaire")
public class FormuleAir extends HttpServlet{

    static HashMap<String, String> users = new HashMap<String, String>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//FR\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>Formule Air</TITLE></HEAD>\n" +
                "<BODY>\n" +
                "<center>Formule Air</center>\n" +
                "<br>");

        String userToDelete = request.getParameter("suppr");
        if (users.containsKey(userToDelete)) {
            users.remove(userToDelete);
            out.println("User " + userToDelete + " deleted <br>");
        }

        String userToAdd = request.getParameter("add");
        if (userToAdd != null) {
            users.put(userToAdd, "password");
            out.println("User " + userToAdd + " added <br>");
        }



        out.println("""
                <form action="/annuaire" method="post">
                    <label for="mail">mail:</label>
                    <input type="text" id="mail" name="mail" required>
                    <label for="pass">pass:</label>
                    <input type="pass" id="pass" name="pass" required>
                    <button type="submit">Login</button>
                </form>
                """);

        out.println("<br>");
        out.println("Users: <br>");
        out.println("<br>");

        for (String key : users.keySet()) {
            out.println(key + "<br>");
        }

        out.println("</BODY>\n" +
                "</HTML>");


        out.flush();
        out.close();

}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("mail");
        String password = request.getParameter("pass");


        users.put(name, password);

        doGet(request, response);
    }

}
