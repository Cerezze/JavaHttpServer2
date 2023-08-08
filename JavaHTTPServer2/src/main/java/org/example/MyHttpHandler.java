package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        OutputStream outputStream = httpExchange.getResponseBody();

        if(httpExchange.getRequestMethod().equals("GET")){
            Employee e = new Employee();
            byte[] array = new byte[e.test1().size()];

            for(int i = 0; i < e.test1().size(); i++){
                array[i] = e.test1().get(i);
            }

            httpExchange.sendResponseHeaders(200, array.length);
            outputStream.write(array);
            outputStream.flush();
            outputStream.close();
        }
        if(httpExchange.getRequestMethod().equals("POST")){
            InputStream inStream = httpExchange.getRequestBody();

            int i = 0;
            while(i != -1){
                i = inStream.read();
                System.out.println("NUM:: " + i);
            }
            System.out.println("POOOSTTT");
            inStream.close();
        }
    }
}
