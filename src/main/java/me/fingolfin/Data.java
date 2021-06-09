package me.fingolfin;

import java.sql.*;

public class Data implements AutoClosable {
  public static String jdbcUrl = "jdbc:sqlite:database/database.db";

    private Connection con;
    private Statement stmnt;

    public Data() throws SQLException {
        try {
            con = DriverManager.getConnection(jdbcUrl);
            stmnt = con.createStatement();
        } finally {
            if (stmnt == null) {
                con.close();
                System.out.println("unsuccessfull initiating databank");
            }
        }
    }
  
  public ResultSet getResults(String query) {
        try {
            con = DriverManager.getConnection(jdbcUrl);
            stmnt = con.createStatement();
            ResultSet set = stmnt.executeQuery(query);
            return set;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("query gone wrong");
            return null;
        }
    }
  
   @Override
    public void close() throws Exception {
        con.close();
    }
}
