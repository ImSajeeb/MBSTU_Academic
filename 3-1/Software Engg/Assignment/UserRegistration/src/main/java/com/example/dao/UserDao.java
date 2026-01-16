package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.example.model.User;

public class UserDao {

	public static int save(User user) {
	    int status = 0;

	    try {
	        System.out.println("Loading driver...");
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        System.out.println("Connecting to database...");
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/userdb?useSSL=false&serverTimezone=UTC",
	            "root",
	            "1234"
	        );

	        System.out.println("Connected!");

	        String sql = "INSERT INTO users(name,email,password) VALUES (?,?,?)";
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, user.getName());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getPassword());

	        status = ps.executeUpdate();
	        System.out.println("Rows inserted: " + status);

	        ps.close();
	        con.close();

	    } catch (Exception e) {
	        System.out.println("ERROR OCCURRED:");
	        e.printStackTrace();
	    }

	    return status;
	}

}
