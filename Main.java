package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AccountManager manager = new AccountManager();
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
                        while (true) {
                            MenuPrompt.printMenu(2);
                            switch (scan.nextInt()) {
                            case 1:
                                manager.showBalance();
                                break;
                            case 2:
                                manager.logOutAccount();
                                break;
                            case 0:
                                System.out.println("\nBye!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid choice. Try again!");
                        }
                        }
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