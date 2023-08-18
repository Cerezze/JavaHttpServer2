package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            server.createContext("/test", new MyHttpHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
            System.out.println("Server started on port 8080");

            //DB SHOULD BE IMPLEMENTED AS SINGLETON EVENTUALLY
            Db db = new Db();
            Connection connection = db.getConnection();

            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}