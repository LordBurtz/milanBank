package me.fingolfin;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    static Map<String, Object> data;

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

    //TODO: add yml config option
    public static void getConfig() {
        Yaml yaml = new Yaml();
        try {
            InputStream in = new FileInputStream("config.yml");
            data = yaml.load(in);
            System.out.println(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //getConfig();
        Bank bank =Bank.getInstance();
        bank.getCustomerInfoByID("1");
        bank.getCustomerInfoByID("2");
        bank.transfer("1", "2", 420);
        bank.getCustomerInfoByID("1");
        bank.getCustomerInfoByID("2");
    }
}
