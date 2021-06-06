package me.fingolfin;

import java.sql.*;
import java.util.Map;

public class Main {
    public static void test() {
        String jdbcUrl = "jdbc:sqlite:database/database.db";
        try {
            Connection con = DriverManager.getConnection(jdbcUrl);
            String test_request = "select * from users";

            Statement stmnt = con.createStatement();
            ResultSet res = stmnt.executeQuery(test_request);

            System.out.println("test request: ");
            while (res.next()) {
                String name = res.getString("name");
                String email = res.getString("email");

                System.out.println(name + " | " + email);
            }

            String create_customers = "create table if not exists customers(id integer PRIMARY KEY, name varchar(20), " +
                    "surname varchar(30), worth integer);";
            String insert_customer = "insert into customers (id, name, surname, worth) values(\"1\", \"karger\", \"fridolin\", \"42069\");";
            stmnt.execute(create_customers);
            stmnt.execute(insert_customer);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        Bank bank =Bank.getInstance();
        bank.getCustomers();
        bank.addCustomer("deml", "milan", "17");
        bank.getCustomers();
    }
}
