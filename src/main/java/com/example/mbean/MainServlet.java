package com.example.mbean;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/main")
public class MainServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("com.example.mbean:type=Hello");
            App mbean = new App();
            mbs.registerMBean(mbean, name);

            ServletContextEvent sce = new ServletContextEvent(getServletContext());
            sce.getServletContext().setAttribute("myHelloMBean", mbean);

        } catch (Exception e) {
            throw new ServletException("Failed to register MBean", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println("MBean registered. Check with JConsole under com.example.mbean:type=AppMBean");
    }
}