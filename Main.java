package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice;
        String currentAccount = "";
        while (true) {
            MenuPrompt.printMenu(1);
            choice = scan.nextInt();
            if (choice == 1) {
                continue;
            } else if (choice == 2) {
                continue;
            } else if (choice == 0) {
                break;
            } else {
                System.out.println("Invalid choice. Try again!");
            }
        }
    }
}