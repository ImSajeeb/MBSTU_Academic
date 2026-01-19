package com.sajeeb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {

    public void insertData(String s){
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "INSERT INTO student (name) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s);
            ps.executeUpdate();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getData(){
        try {
            String sql = "SELECT * FROM student";
            Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getString("name"));
            }
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
