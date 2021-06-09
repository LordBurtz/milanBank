package me.fingolfin;

import java.sql.*;

import static me.fingolfin.SQL.jdbcUrl;

public class Bank {
    private static Bank bank = new Bank();
    private Data data;
    private Bank() {        data = new Data();}

    public static Bank getInstance() {
        return bank;
    }

    public void getCustomers() throws Exception{ //added bc its whining about the "unhandled exception i handled somehwere else bruh
        ResultSet set = data.getResults("select name, surname, id from customers;");
        if (set == null) return;
        while (set.next()) {
            String name =set.getString("name");
            String surname = set.getString("surname");
            System.out.println(name + " | " + surname);
        }
    }

    public void addCustomer(String name, String surname, String worth) throws Exception {
        String[] rows = {"name", "surname", "worth"};
        String[] values = {name, surname, worth};
        data.insert("customers", rows, values);
    }

    public void getCustomerInfoByName(String name) throws Exception{
        ResultSet set = data.getResults("select * from customers where name=\"" +name+"\";");
        if (set == null) {
            System.out.println(name + " not found!");
            return;
        }
        System.out.println("Displaying results for " +name);
        System.out.println("Full name: " + set.getString("surname") + " " + set.getString("name"));
        System.out.println("ID: " + set.getString("id"));
        System.out.println("Net Worth: " + set.getString("worth"));
    }

    public void getCustomerInfoByID(String id) throws Exception{
        ResultSet set = data.getResults("select * from customers where id=\"" +id+"\";");
        if (set == null) {
            System.out.println(id + " not found!");
            return;
        }
        System.out.println("Displaying results for ID: " +id);
        System.out.println("Full name: " + set.getString("surname") + " " + set.getString("name"));
        System.out.println("ID: " + set.getString("id"));
        System.out.println("Net Worth: " + set.getString("worth"));
    }

    public void setDatabankUp() {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl);
            Statement stmnt = con.createStatement();
            String create_customers = "create table if not exists customers(id integer PRIMARY KEY, name varchar(20), "
                    + "surname varchar(30), worth integer, PLZ varchar(10), addr text, created_at varchar(22));";
            System.out.println("database up and running");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("could not setup database");
        }
    }

    public void updateCustomer(String id, String row, String value) throws Exception {
        ResultSet set = data.getResults(String.format("select %s from customers where id = \"%s\";", row, id));
        System.out.println(String.format("select %s from customers where id = \"%s\";", row, id));
        assert set != null;
        String val_old = set.getString(row);
        data.updateVal("customers", row, value, "id", id);
        System.out.printf("changed %s to %s%n", val_old, value);
    }
    
    //TODO: working on worth
    public void transfer(String senderID, String recieverID, double amount) {
        if (amount < 0) {
            System.out.println("negative ammounts not supported");
            return;
        }
        ResultSet set = data.getResults("select worth from customers where id  = \"" + senderID + "\"");
        int amount_old = set.getInt("worth");
        int new_amount = amount_old - amount;
        updateCustomer(senderID, worth, new_amount);
        
        System.out.println("sender updated");
        
        ResultSet set = data.getResults("select worth from customers where id  = \"" + recieverID + "\"");
        int amount_old = set.getInt("worth");
        int new_amount = amount_old + amount;
        updateCustomer(senderID, worth, new_amount);
        
        System.out.println("receiver updated");
}
