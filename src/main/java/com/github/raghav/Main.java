package com.github.raghav;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            String driverName = "org.apache.hive.jdbc.HiveDriver";
            Class.forName(driverName);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/acid", "", "");
        Statement stmt = con.createStatement();
        String tableName = "tbl1";
        stmt.execute("create table " + tableName + " (id int) STORED AS ORC TBLPROPERTIES" +
                     "('transactional'='true')");

        String sql = "describe formatted " + tableName;
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);

        while(res.next())
            System.out.println(res.getString(1) + "\t" + res.getString(2));

        stmt.close();
        con.close();
    }

}

