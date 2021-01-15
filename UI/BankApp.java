package banking.UI;

import banking.Accounts.AccountManager;

import java.util.Scanner;

public class BankApp {
    public static void start(AccountManager manager) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            MenuPrompt.printMenu(1);
            switch (scan.nextInt()) {
                case 1:
                    manager.createAccount();
                    break;
                case 2:
                    System.out.println("Enter your card number:");
                    String number = scan.next();
                    System.out.println("Enter your PIN:");
                    String pin = scan.next();
                    if (manager.logInAccount(number, pin)) {
                        System.out.println("You have successfully logged in!");
                        AccountApp.start(manager);
                    } else {
                        System.out.println("Wrong card number or PIN!");
                    }
                    break;
                case 0:
                    System.out.println("\nBye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
