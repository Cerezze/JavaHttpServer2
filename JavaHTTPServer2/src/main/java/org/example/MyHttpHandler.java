package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException{
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        OutputStream outputStream = httpExchange.getResponseBody();

        if(httpExchange.getRequestMethod().equals("GET")){
            Employee e = new Employee();
            int size = e.test1().size();
            byte[] array = new byte[size];

            for(int i = 0; i < size; i++){
                array[i] = e.test1().get(i);
            }

            httpExchange.sendResponseHeaders(200, array.length);
            outputStream.write(array);
            outputStream.flush();
            outputStream.close();
        }
        if(httpExchange.getRequestMethod().equals("POST")){
            InputStream inStream = httpExchange.getRequestBody();
            //ArrayList<String> arrList = new ArrayList<>();
            StringBuilder s = new StringBuilder();
            int i = inStream.read();

            while(i != -1) {
                    //System.out.println("NUM:: " + i);
                    s.append((char) i);

                    i = inStream.read();
            }
            //parse the byte stream
            System.out.println(s);
            String s2 = "\""+ s + "\"";
            //String s2 = "{\"Student_ID\":10203,\"Department\":\"ME\",\"First_Name\":\"Dan\",\"Last_Name\":\"Kyritz\",\"PassOutYear\":2020,\"UniversityRank\":402}";
            System.out.println(s2);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("IT IS DONE!");
            Student student = new Student();


            try {
                student = objectMapper.readValue(s2, Student.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Db db = null;
            try {
                db = new Db();
                Connection connection = db.getConnection();
                db.insertRecord(student.Student_ID, student.Department, student.First_Name, student.Last_Name, student.PassOutYear, student.UniversityRank);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //System.out.println(student.Student_ID);
            //place it in mysql
            System.out.println("IT IS DONE!");
            inStream.close();
        }
    }
}
