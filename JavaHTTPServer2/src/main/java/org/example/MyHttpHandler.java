package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) {
        System.out.println("REQUEST_METHOD::" + httpExchange.getRequestMethod());
    }
}
