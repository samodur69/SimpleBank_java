package banking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AccountManager {
    private Account currentAccount;
    public List<Account> cardAccounts = new ArrayList<>();

    public void createAccount() {
        /**
         * create new Account object. Print number/pin, add to accounts list
         */
        System.out.println("Your card has been created");
        Account acc = new Account();
        this.cardAccounts.add(acc);
        System.out.println(Arrays.toString(this.cardAccounts.toArray()));
    }

    public boolean logInAccount (String inputNumber, String inputPin){
        for (Account acc: this.cardAccounts) {
            if (inputNumber.equals(acc.getCardNumber())) {
                if (inputPin.equals(acc.getCardPin())) {
                    this.currentAccount = acc;
                    System.out.println(this.currentAccount.toString());
                    return true;
                }
            }
        }
        System.out.println(this.currentAccount.toString());
        return false;
    }

    protected void logOutAccount () {
        this.currentAccount = null;
        System.out.println("You have successfully logged out!");
    }

    protected void showBalance() {
        System.out.println(this.currentAccount.getCardBalance());
    }

}
