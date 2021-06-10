package me.fingolfin;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleInput {
    public boolean quit = false;

    private Bank bank;

    public ConsoleInput(Bank bank) {
        this.bank = bank;

        Scanner scan = new Scanner(System.in);
        System.out.println("Username: ");
        String us = scan.next();
        System.out.println("usr: " + us);

        while (!quit) {
            System.out.println("\n-------------------------------------\n");
            System.out.println(" Actions available: "+ "\n [1] to get all users" +
                    "\n [2] to add a customer" + "\n [3] get specific customer info by name" +
                    "\n [4] get specific customer info by id" + "\n [5] to update customer specific data" +
                    "\n [6] to transfer money" + "\n [7] to quit" + "\n\u001B[33m [8] for credits\u001B[0m");
            System.out.println("\n-------------------------------------\n");
            String next = scan.next();

            switch (next) {
                case "1":
                    try {
                        bank.getCustomers();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                        break;

                case "2":
                    System.out.println(" adding new customer\n supply info here:");
                    prn(" Name: ");
                    String name = scan.next();
                    prn(" surname: ");
                    String surname = scan.next();
                    prn(" initial starting amount");
                    String worth = scan.next();
                    prn(" PLZ: ");
                    String plz = scan.next();
                    prn(" Address: ");
                    String address = scan.next();
                    bank.addCustomer(name, surname, worth, plz, address);
                    System.out.println(" successfully added");
                    break;

                case "3":
                    prn(" name: ");
                    String name3 = scan.next();
                    try {
                        bank.getCustomerInfoByName(name3);
                    } catch (SQLException ignored) {

                    }
                    break;

                case "4":
                    prn(" ID: ");
                    String name4 = scan.next();
                    try {
                        bank.getCustomerInfoByID(name4);
                    } catch (SQLException ignored) {

                    }
                    break;

                case "5":
                    prn(" you have to supply an ID and a row value that should be changed");
                    prn(" ID: ");
                    String id5 =  scan.next();
                    prn(" row: ");
                    String row5 = scan.next();
                    prn(" value: ");
                    String val5 = scan.next();
                    try {
                        bank.updateCustomer(id5, row5, val5);
                    } catch (SQLException ignored) {

                    }
                    break;

                case "6":
                    prn(" transferring money! only positive amounts allowed");
                    prn(" ID of the sending person: ");
                    String sendID = scan.next();
                    prn(" ID of the receiving person: ");
                    String recvID = scan.next();
                    prn(" amount to be transferred: ");
                    double amount = scan.nextDouble();
                    bank.transfer(sendID, recvID, amount);
                    break;

                case "7":
                    System.console().flush();
                    System.out.println(" quitting..");
                    System.exit(0);
                    quit = true;
                    break;

                case "8":
                    System.console().flush();
                    prn(" credits: ");
                    prn(" ________________________________________________________________ \n" +
                            "|  ____________________________________________________________  |\n" +
                            "| | Credit Fingolfin\n" +
                            "github: LordBurtz\n" +
                            "Discord: Fingolfin#5731 | |\n" +
                            "| |____________________________________________________________| |\n" +
                            "|________________________________________________________________|");
                    prn(" repo: https://github.com/LordBurtz/milanBank");
                    break;


                default:
                    System.out.println("\u001B[31m !! not a supported option !! \u001B[0m");
                    break;
            }
        }
    }

    public static void prn(String prn) {
        System.out.println(prn);
    }
}
