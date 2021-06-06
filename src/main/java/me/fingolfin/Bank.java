package me.fingolfin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bank {
    private static Bank bank = new Bank();
    private Bank() {}

    public static Bank getInstance() {
        return bank;
    }

    public void getCustomers() throws Exception{ //added bc its whining about the "unhandled exception i handled somehwere else bruh
        ResultSet set = SQL.getResults("select name, surname, id from customers;");
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
        SQL.insert("customers", rows, values);
    }

    public void getCustomerInfoByName(String name) {

    }
}
