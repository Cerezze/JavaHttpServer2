package org.example;

import java.sql.*;

public class Db {
    private String url = "jdbc:mysql://localhost:3306/university";
    private String username = "root";
    private String password = "MyNewPass";
    private Connection connection;


    public Db() throws SQLException{
        this.connection = DriverManager.getConnection(
                url, username, password
        );
    }

    public void setUpConnection() throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultset = statement.executeQuery("select * from engineeringstudents");

        while(resultset.next()){
            System.out.println(resultset.getInt(1) + resultset.getString(2));
        }
    }

    public void insertRecord(int Student_ID, String Department, String First_Name, String Last_Name, int PassOutYear, int UniversityRank) throws SQLException{
        Statement statement = connection.createStatement();

        statement.executeUpdate("INSERT INTO engineeringstudents " +
                "VALUES (" + Student_ID + ", " + Department + ", "+ First_Name + ", " +Last_Name+ ", "+ PassOutYear + ", "+ UniversityRank+")");
    }

    public Connection getConnection() {
        return connection;
    }
}
