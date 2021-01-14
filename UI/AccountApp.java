package banking.UI;

import banking.Accounts.AccountManager;
import java.util.Scanner;

public class AccountApp {
    public static void start(AccountManager manager) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            MenuPrompt.printMenu(2);
            switch (scan.nextInt()) {
                case 1:
                    manager.showBalance();
                    break;
                case 2:
                    manager.logOutAccount();
                    BankApp.start(manager);
                    break;
                case 0:
                    System.out.println("\nBye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
