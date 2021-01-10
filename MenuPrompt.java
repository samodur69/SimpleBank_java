package banking;

public class MenuPrompt {
    private static String mainMenu = "\n1.Create an account\n2.Log into account\n0.Exit";
    private static String accountMenu = "\n1.Balance\n2.Log out\n0.Exit";

    public static void printMenu(int menuCode) {
        if (menuCode == 1) {
            System.out.println(mainMenu);
        } else if (menuCode == 2) {
            System.out.println(accountMenu);
        }
    }
}
