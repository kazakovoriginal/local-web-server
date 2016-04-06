package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;
import servlets.MJPG;


public class Main {
    public static void main(String[] args) throws Exception {

//************ Создание сервлета для работы с MJPG************

        MJPG mjpgServlet = new MJPG();
        ServletContextHandler contextMJPG = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextMJPG.addServlet(new ServletHolder(mjpgServlet), "/*");


        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");
//        context.setContextPath("/");


//************ Создание сервера ************
        Server server = new Server(8081);
        server.setHandler(contextMJPG);

//************ Создание сервлета для работы с MJPG************
//        ServletHolder jerseyServlet = context.addServlet(
//                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
//        jerseyServlet.setInitOrder(0);
//
//        jerseyServlet.setInitParameter(
//                "jersey.config.server.provider.classnames",
//                Hello.class.getCanonicalName());

//************ Запуск сервера************
        server.start();
        server.join();



    }
}
