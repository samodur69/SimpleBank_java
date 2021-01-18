package banking.UI;

import banking.Accounts.Account;
import banking.Accounts.AccountManager;

import java.sql.SQLException;
import java.util.Scanner;

public class AccountApp {
    public static void start(AccountManager manager) throws SQLException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            MenuPrompt.printMenu(2);
            switch (scan.nextInt()) {
                case 1:
                    manager.showBalance();
                    break;
                case 2:
                    System.out.println("Enter income:");
                    manager.addIncome(scan.nextInt());
                    break;
                case 3:
                    System.out.println("Transfer\nEnter card number:");
                    String cardNumber = scan.next();
                    if (Account.checkSumValidation(cardNumber)) {
                        if (manager.checkTargetAccount(cardNumber)) {
                            System.out.println("Enter how much money you want to transfer:");
                            manager.makeTransaction(cardNumber, scan.nextInt());
                        } else {
                            System.out.println("Such card does not exist.");
                        }
                    } else {
                        System.out.println("Probably you made mistake in card number. Please try again!");
                    }
                    break;
                case 4:
                    manager.closeAccount();
                    BankApp.start(manager);
                    break;
                case 5:
                    manager.logOutAccount();
                    BankApp.start(manager);
                    break;
                case 0:
                    manager.logOutAccount();
                    System.out.println("\nBye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
