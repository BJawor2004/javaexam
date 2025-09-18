package org.example;

import java.sql.*;
public class ConnectToDB {
    public void connect()
    {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "student";
        String password = "student1";

        try(Connection con = DriverManager.getConnection(url, user, password))
        {
            System.out.println("Połączono z bazą");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
