package com.sajeeb;

import java.sql.*;
public class DBUtil {
    static final String url = "jdbc:mysql://localhost:3306/testdb";

    static final String user = "root";
    static final String pass = "1234";

   // Connection conn = DriverManager.getConnection(url,user,pass);

    public static Connection getConnection() throws Exception{
        return  DriverManager.getConnection(url,user,pass);
    }

}
