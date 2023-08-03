package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("REQUEST_METHOD::" + httpExchange.getRequestMethod());

        httpExchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        byte[] array = new byte[25];

        InputStream inStream = new FileInputStream("C://Users//cb//Desktop//Executable_Code_And_Projects//FullProjects//JavaHttpServer2//JavaHTTPServer2//src//main//java//org//example//test.json");

        OutputStream outputStream = httpExchange.getResponseBody();

        //StringBuilder htmlBuilder = new StringBuilder();
        if(httpExchange.getRequestMethod().equals("GET")){
            httpExchange.sendResponseHeaders(200, array.length);
            inStream.read(array);
            /*for(byte i: array){
                System.out.println(i);
            }*/
            outputStream.write(array);
            outputStream.flush();
            inStream.close();
            outputStream.close();
        }
        if(httpExchange.getRequestMethod().equals("POST")){

            inStream = httpExchange.getRequestBody();
            inStream.read(array);
            for(byte i: array){
                System.out.println(i);
            }

            System.out.println("POOOSTTT");
            inStream.close();
        }
    }
}
