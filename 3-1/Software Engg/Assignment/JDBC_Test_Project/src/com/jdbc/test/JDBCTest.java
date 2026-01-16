package com.jdbc.test;

import java.sql.Connection; 

public class JDBCTest {

    public static void main(String[] args) {

        Connection con = DBUtil.getConnection();

        if (con != null) {
            System.out.println("🎉 JDBC CONNECTION VERIFIED");
        } else {
            System.out.println("❌ JDBC CONNECTION FAILED");
        }
    }
}
