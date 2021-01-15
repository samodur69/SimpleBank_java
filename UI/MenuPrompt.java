package banking.UI;

public class MenuPrompt {
    private final static String mainMenu = "\n1.Create an account\n2.Log into account\n0.Exit";
    private final static String accountMenu = "\n1.Balance\n2.Add income\n3.Do transfer\n4.Close account\n5.Log out\n0.Exit";

    public static void printMenu(int menuCode) {
        switch (menuCode) {
            case 1:
                System.out.println(mainMenu);
                break;
            case 2:
                System.out.println(accountMenu);
                break;
            default:
                System.out.println("Input args");
        }
    }
}
