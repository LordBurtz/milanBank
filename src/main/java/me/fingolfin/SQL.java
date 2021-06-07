package me.fingolfin;

import java.sql.*;

public class SQL {
    public static String jdbcUrl = "jdbc:sqlite:database/database.db";

    public static ResultSet getResults(String query) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl);
            Statement stmnt = con.createStatement();
            return stmnt.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("query gone wrong");
            return null;
        }
    }

    public static void insert(String table, String[] rows, String[] values) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl);
            Statement stmnt = con.createStatement();

            String query = "insert into " + table + " (";
            for (String s : rows) {
                 query = query + "\""+  s +"\",";
            }
            query = query.substring(0, query.length() -1);
            query = query + ") values (";
            for (String s : values) {
                query = query + "\""+  s +"\",";
            }
            query = query.substring(0, query.length() -1);
            query = query + ");";

            stmnt.execute(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("query gone wrong");
        }
    }

    public static void updateVal (String table, String row, String val, String condition_key, String condition_val) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl);
            Statement stmnt = con.createStatement();
            String query = String.format("update %s set %s = \"%s\" where %s = \"%s\";", table, row, val,
                    condition_key, condition_val);
            stmnt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("query gone wrong");
        }
    }

    //TODO: move all methods in here
}
