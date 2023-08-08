package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Employee {
    public ArrayList<Byte> test1() throws FileNotFoundException {
        //FileReader f = new FileReader("C://Users//cb//Desktop//Executable_Code_And_Projects//FullProjects//JavaHttpServer2//JavaHTTPServer2//src//main//java//org//example//test.json");
        ArrayList<Byte> result = new ArrayList<>();

        try (FileReader f = new FileReader("C://Users//cb//Desktop//Executable_Code_And_Projects//FullProjects//JavaHttpServer2//JavaHTTPServer2//src//main//java//org//example//test.json")) {
            //System.out.println((byte)f.read());
            while(f.ready()){
                //System.out.println((byte)f.read());
                result.add((byte)f.read());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(result);
        return result;
    }
}
