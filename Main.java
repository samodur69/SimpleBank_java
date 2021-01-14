package banking;

import banking.Accounts.AccountManager;
import banking.UI.BankApp;


public class Main {
    private static String[] savedArgs;

    /**
     * function to handle command line arguments. Program start with 2 args:
     * -fileName dbname.s3db
     * @return second cmdline argument
     */
    public static String getSavedArgs(){
        return savedArgs[1];
    }

    public static void main(String[] args) {
        //// need to insert args parsing
        savedArgs = args;
        AccountManager manager = new AccountManager();
        FileUtil.checkFile(args[1]);
        BankApp.start(manager);
    }
}