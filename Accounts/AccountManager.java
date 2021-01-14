package banking.Accounts;
import banking.Main;
import banking.data.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AccountManager {
    private Account currentAccount;
//    public List<Account> sessionCardAccounts = new ArrayList<>();
    DatabaseUtil db = new DatabaseUtil(Main.getSavedArgs());

    /**
     * create new Account object. Print number/pin, add to accounts list
     */
    public void createAccount() {

        System.out.println("Your card has been created");
        Account acc = new Account();
//        this.sessionCardAccounts.add(acc);
        db.sqlAddNewAccount(acc);
    }

    /**
     * Login into account.
     * check card/pin pair from DB. if no number in DB - return null
     * @param inputNumber card number from user input
     * @param inputPin card pin from user input
     * @return true if number and pin correct
     */
    public boolean logInAccount (String inputNumber, String inputPin){
        this.currentAccount = db.importAccountInfo(inputNumber, inputPin);
        return this.currentAccount != null;
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
