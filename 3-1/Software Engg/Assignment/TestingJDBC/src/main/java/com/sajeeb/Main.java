package com.sajeeb;


import java.sql.Connection;

public class Main {
    static void main() {
        try {
            Connection conn = DBUtil.getConnection();
            System.out.println("Connected");
            conn.close();
        }
        catch (Exception e ){
            e.printStackTrace();
        }

        DAO dd = new DAO();
        dd.insertData("SK");
       // dd.insertData(new student("Sajeeb"));
        dd.getData();
    }
}
