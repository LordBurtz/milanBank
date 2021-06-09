package me.fingolfin;

import java.util.Scanner;

public class ConsoleInput {
    public boolean quit = false;

    public ConsoleInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Username: ");
        String us = scan.next();
        System.out.println("usr: " + us);

        while (!quit) {
        System.out.println(" Actions available: "+ "\n [1] for user data request" +
                "\n [2] for user data update" + "\n [3] for transaction" +
                "\n [4] to quit");
        String next = scan.next();
        try {
            int i = Integer.parseInt(next);
            if (i == 4) quit = true;
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "this is not a number" + "\u001B[0m");
        }
    }
    }

    public static void main(String[] args) {
        new ConsoleInput();
    }
}
