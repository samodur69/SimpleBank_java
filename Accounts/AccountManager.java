package banking.Accounts;
import banking.Main;
import banking.data.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private Account currentAccount;
    public List<Account> cardAccounts = new ArrayList<>();
    DatabaseUtil db = new DatabaseUtil(Main.getSavedArgs());

    /**
     * create new Account object. Print number/pin, add to accounts list
     */
    public void createAccount() {

        System.out.println("Your card has been created");
        Account acc = new Account();
        this.cardAccounts.add(acc);
        db.sqlAddNewAccount(acc);
    }

    public boolean logInAccount (String inputNumber, String inputPin){
        for (Account acc: this.cardAccounts) {
            if (inputNumber.equals(acc.getCardNumber())) {
                if (inputPin.equals(acc.getCardPin())) {
                    this.currentAccount = acc;
                    System.out.println("You have successfully logged in!");
                    return true;
                }
            }
        }
        return false;
    }

    public void logOutAccount() {
        //  here update DB
        this.currentAccount = null;
        System.out.println("You have successfully logged out!");
    }

    public void showBalance() {
        System.out.println("Balance: " + this.currentAccount.getCardBalance());
    }

}
