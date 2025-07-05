/*
Copyright 2025 Raghav Aggarwal

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.github.raghav;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
  public static void main(String[] args) throws SQLException {
    try {
      String driverName = "org.apache.hive.jdbc.HiveDriver";
      Class.forName(driverName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }

    Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/raghav", "", "");
    Statement stmt = con.createStatement();
    String tableName = "t2";
    stmt.execute("select `(COUNT)?.` from t2 where id=1");

    String sql = "describe formatted " + tableName;
    System.out.println("Running: " + sql);
    ResultSet res = stmt.executeQuery(sql);

    while (res.next()) System.out.println(res.getString(1) + "\t" + res.getString(2));

    stmt.close();
    con.close();
  }
}
