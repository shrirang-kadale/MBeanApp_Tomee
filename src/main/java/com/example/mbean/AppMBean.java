package com.example.mbean;

public interface AppMBean {
        void sayHello();
        int add(int x, int y);
        String getName();
        void setName(String name);
        String getAppName();
}