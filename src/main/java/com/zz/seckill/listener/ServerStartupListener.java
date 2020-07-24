package com.zz.seckill.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 获取web应用对象
        ServletContext application = sce.getServletContext();

        // 获取当前的上下文路径
        String path = application.getContextPath();

        // 保存路径到web应用范围中，让所有的用户访问
        application.setAttribute("APP_PATH", path);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
