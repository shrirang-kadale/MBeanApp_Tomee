package com.example.mbean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class App implements AppMBean {
    private String name = "MBeanApp";

    @Override
    public void sayHello() {
        System.out.println("Hello, " + name + "!");
    }

    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAppName() {
        return "";
    }

    @PostConstruct
    public void init() {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("com.example.jmx:type=Hello");
            mbs.registerMBean(this, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("com.example.jmx:type=Hello");
            mbs.unregisterMBean(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}