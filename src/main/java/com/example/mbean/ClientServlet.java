package com.example.mbean;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;


//@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        try {

            response.getWriter().println("Auto registered MBean with ServletContextListner");
            App app = (App) getServletContext().getAttribute("myAppBean");

            app.sayHello();
            response.getWriter().println("Result of add(2, 3): " + app.add(2, 3));
            response.getWriter().println("Current Name: " + app.getName());
            app.setName("TomEE");
            response.getWriter().println("New Name: " + app.getName());

            response.getWriter().println("=================================================================================");

            response.getWriter().println("Manually registered MBean");
            App hello = (App) getServletContext().getAttribute("myHelloMBean");

            hello.sayHello();
            response.getWriter().println("Result of add(2, 3): " + hello.add(2, 3));
            response.getWriter().println("Current Name: " + hello.getName());
            hello.setName("TomEE");
            response.getWriter().println("New Name: " + hello.getName());

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    }