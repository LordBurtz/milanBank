package me.fingolfin.backend;

import java.sql.*;

@Deprecated
public class SQL implements AutoCloseable{
    public static String jdbcUrl = "jdbc:sqlite:database/database.db";

    private Connection con;
    private Statement stmnt;

    @Deprecated
    public SQL() throws SQLException {
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

    public static ResultSet getResults(String query) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl);
            Statement stmnt = con.createStatement();
            ResultSet set = stmnt.executeQuery(query);
            stmnt.close();
            return set;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("query gone wrong");
            return null;
        }
    }

    //TODO: make this work https://stackoverflow.com/questions/15593170/jdbc-sql-database-is-locked
    public ResultSet getResults3(String query) {
            try {
                ResultSet set = stmnt.executeQuery(query);
                stmnt.close();
                return set;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("query gone wrong");
                return null;
            }
    }


    public static ResultSet getResults2(String query) {
        try (SQL sql = new SQL()) {
            sql.getResults3(query);

        } catch (Exception  throwables) {
            throwables.printStackTrace();
            System.out.println("query gone wrong");
            return null;
        }
        return null;
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

    //TODO: move all methods in here
}
