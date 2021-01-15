package banking.Accounts;
import banking.Main;
import banking.data.DatabaseUtil;

/**
 *
 */
public class AccountManager {
    private Account currentAccount;
    DatabaseUtil db = new DatabaseUtil(Main.getSavedArgs());

    /**
     * create new Account object. Print number/pin, add to accounts list
     */
    public void createAccount() {
        System.out.println("Your card has been created");
        Account acc = new Account(0);
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
        db.updateAccountBalance(this.currentAccount);
        this.currentAccount = null;
        System.out.println("You have successfully logged out!");
    }

    public void showBalance() {
        System.out.println("Balance: " + this.currentAccount.getCardBalance());
    }

    public void addIncome(int amount) {
        // try catch and check income
        this.currentAccount.accountTransaction("+", amount);
        System.out.println("Income was added!");
    }


    public boolean checkMoneyTransfer(int amount) {
        return this.currentAccount.getCardBalance() - amount >= 0;
    }

    public void closeAccount() {
        db.deleteAccount(this.currentAccount.getCardNumber());
        System.out.println("The account has been closed!");
    }

    public boolean checkTargetAccount(String accountNumber) {
        return db.checkTargetCardNumber(accountNumber);
    }

    public void makeTransaction(String targetNumber, int amount) {
        if (checkMoneyTransfer(amount)) {
            this.currentAccount.accountTransaction("-", amount);
            db.makeTransaction(targetNumber, amount);
            System.out.println("Success!");
        } else {
            System.out.println("Not enough money!");
        }
    }

}
