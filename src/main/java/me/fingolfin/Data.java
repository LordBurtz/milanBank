package me.fingolfin;

import java.sql.*;

public class Data implements AutoCloseable {
  public static String jdbcUrl = "jdbc:sqlite:database/database.db";

    private Connection con;
    private Statement stmnt;

    public Data() {
        try {
            con = DriverManager.getConnection(jdbcUrl);
            stmnt = con.createStatement();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }finally {
            if (stmnt == null) {
              try{
                con.close();
                System.out.println("unsuccessfull initiating databank");
            } catch (SQLException ex) {
                ex.printStackTrace();
              }
        }}
    }
  
  public ResultSet getResults(String query) {
        try {
            ResultSet set = stmnt.executeQuery(query);
            return set;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("query gone wrong");
            return null;
        }
    }
  
   public void insert(String table, String[] rows, String[] values) {
        try {
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
  
   public void updateVal (String table, String row, String val, String condition_key, String condition_val) {
        try {
            String query = String.format("update %s set %s = \"%s\" where %s = \"%s\";", table, row, val,
                    condition_key, condition_val);
            System.out.println(query);
            stmnt.execute(query);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("query gone wrong");
        }
    }
  
   @Override
    public void close() throws Exception {
        con.close();
    }
}
