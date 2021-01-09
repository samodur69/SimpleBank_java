package banking;

import java.util.ArrayList;
import java.util.List;

class AccountManager {
    private Account currentAccount;
    public List<Account> cardAccounts = new ArrayList<>();

    public void createAccount() {
        System.out.println("Your card has been created");
        Account acc = new Account();
        this.cardAccounts.add(acc);
    }

    public boolean logInAccount (String inputNumber, String inputPin){
        for (Account elem: cardAccounts) {
            if (elem.getCardNumber().equals(inputNumber)) {
                System.out.println("Try to login");
                return true;
            } else {
                System.out.println("Invalid card number. Please try again");
            }
        }
        return false; //////
    }

    protected void logOutAccount () {
//        this.currentAccount = "";/////////////
        System.out.println("log out complete");
    }


}
