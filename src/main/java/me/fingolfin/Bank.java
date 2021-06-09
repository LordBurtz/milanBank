package me.fingolfin;

import java.sql.*;

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

    public void addCustomer(String name, String surname, String worth, String plz, String addr) {
        String[] rows = {"name", "surname", "worth", "PLZ", "addr", "created_at"};
        String[] values = {name, surname, worth, plz, addr};
        data.insert("customers", rows, values);
    }

    public void getCustomerInfoByName(String name) throws SQLException{
        ResultSet set = data.getResults("select * from customers where name=\"" +name+"\";");
        if (set == null) {
            System.out.println(name + " not found!");
            return;
        }
        System.out.println("Displaying results for " +name);
        System.out.println("Full name: " + set.getString("surname") + " " + set.getString("name"));
        System.out.println("ID: " + set.getString("id"));
        System.out.println("Net Worth: " + set.getString("worth"));
        System.out.println("PLZ: " + set.getString("PLZ"));
        System.out.println("Address: " + set.getString("addr"));
        System.out.println("Account Created at: " + set.getString("created_at"));
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
        System.out.println("PLZ: " + set.getString("PLZ"));
        System.out.println("Address: " + set.getString("addr"));
        System.out.println("Account Created at: " + set.getString("created_at"));
    }

    public void setDatabankUp() {
            String create_customers = "create table if not exists customers(id integer PRIMARY KEY, name text, "
                    + "surname text, worth real, PLZ text, addr text, created_at text);";
            System.out.println("database up and running");
            data.run(create_customers);
    }

    public void updateCustomer(String id, String row, String value) throws SQLException {
        ResultSet set = data.getResults(String.format("select %s from customers where id = \"%s\";", row, id));
        assert set != null;
        String val_old = set.getString(row);
        data.updateVal("customers", row, value, "id", id);
        System.out.printf("changed %s to %s%n", val_old, value);
    }
    
    //TODO: working on worth, should change amount to double later
    public void transfer(String senderID, String recieverID, int amount) {
        if (amount < 0) {
            System.out.println("negative ammounts not supported");
            return;
        }
        try {
        ResultSet set = data.getResults("select worth from customers where id  = \"" + senderID + "\"");
        int amount_old = set.getInt("worth");
        int new_amount = amount_old - amount;
        updateCustomer(senderID, "worth", String.valueOf(new_amount));
        
        System.out.println("sender updated");
        
        set = data.getResults("select worth from customers where id  = \"" + recieverID + "\"");
        amount_old = set.getInt("worth");
        new_amount = amount_old + amount;
        updateCustomer(recieverID, "worth", String.valueOf(new_amount));
        
        System.out.println("receiver updated");
            //TODO: add accurate Exception for parsing String to Integer and SQLException
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
