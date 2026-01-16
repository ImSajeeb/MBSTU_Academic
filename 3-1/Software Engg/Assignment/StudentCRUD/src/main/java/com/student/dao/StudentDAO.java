package com.student.dao;

import java.sql.*;
import java.util.*;
import com.student.model.Student;

public class StudentDAO {

    public static void insert(String name, String email) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                con.prepareStatement(
                    "INSERT INTO students(name,email) VALUES (?,?)"
                );
            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(int id, String name, String email) {
        try {
            Connection con = DBUtil.getConnection();

            PreparedStatement ps =
                con.prepareStatement(
                    "UPDATE students SET name=?, email=? WHERE id=?"
                );

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            System.out.println("UPDATED ROWS = " + rows);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                con.prepareStatement(
                    "DELETE FROM students WHERE id=?"
                );
            ps.setInt(1, id);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        try {
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
                );
                list.add(s);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
