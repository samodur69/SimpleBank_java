package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice;
        while (true) {
            MenuPrompt.printMenu(1);
            choice = scan.nextInt();
            AccountManager manager = new AccountManager();
            if (choice == 1) {
                manager.createAccount();
            } else if (choice == 2) {
                System.out.println("Enter your card number:");
                String number = scan.next();
                System.out.println("Enter your PIN:");
                String pin = scan.next();
                if (manager.logInAccount(number, pin)) {
                    while (true) {
                        MenuPrompt.printMenu(2);
                        choice = scan.nextInt();
                        if (choice == 1) {
                            manager.showBalance();
                        } else if (choice == 2) {
                            manager.logOutAccount();
                            break;
                        } else if (choice == 0) {
                            System.exit(0);
                            break;
                        }
                    }
                } else {
                    System.out.println("Wrong card number or PIN!");
                }
            } else if (choice == 0) {
                System.exit(0);
            } else {
                System.out.println("Invalid choice. Try again!");
            }
        }
    }
}