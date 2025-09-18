package org.example;

import java.sql.*;
public class SendTask {
    public void send()
    {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "student";
        String password = "student1";

        try(Connection con = DriverManager.getConnection(url, user, password))
        {
            String sql = "SELECT ID, COUNTRY FROM WAREHOUSE";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                int id = rs.getInt("ID");
                String country = rs.getString("COUNTRY");
                System.out.println(id + ". " + country);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
