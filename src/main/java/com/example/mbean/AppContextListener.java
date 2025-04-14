package com.example.mbean;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import java.lang.management.ManagementFactory;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            App mbean = new App();
            ObjectName name = new ObjectName("com.example.mbean:type=AppMBean");
            mbs.registerMBean(mbean, name);
            System.out.println("MBean registered.");

            sce.getServletContext().setAttribute("myAppBean", mbean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context destroyed.");
    }
}
