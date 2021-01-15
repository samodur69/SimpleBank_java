package banking.Accounts;
import java.util.Random;

public class Account {
    private int userId;
    private String cardNumber;
    private String cardPin;
    private int cardBalance;

    /**
     * Initialization - set balance 0, print account info (card, pin)
     */
    public Account (int balance) {
        this.userId = generateUserID();
        this.cardNumber = generateCardNumber();
        this.cardPin = generatePinCode();
        this.cardBalance = balance;
        printAccountInfo();
    }

    /**
     * new black account fo db import use
     */
    public Account () {
    }


    /**
     function to generate new account number.
     @return String number
     */
    private static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNum = new StringBuilder("400000");
        for (int i = 0; i < 9; i++) {
            cardNum.append(random.nextInt(10));
        }
        cardNum.append(checkSumGenerator(cardNum.toString()));
        return cardNum.toString();
    }


    /**
     * function for generation Pin
     * @return pin as String
     */
    protected static String generatePinCode() {
        Random random = new Random();
        StringBuilder pinCode = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            pinCode.append(random.nextInt(10));
        }
        return pinCode.toString();
    }

    /**
     * function for generation userID for sql DB
     * @return user as Int
     */
    protected static int generateUserID() {
        Random random = new Random();
        return random.nextInt(10000000);
    }

    /**
     * Check Sum Luhn alg generator.
     * @param cardNum 15 card digits
     * @return last digit
     */
    public static int checkSumGenerator(String cardNum) {
        int sum = 0;
        for (int i = 0; i < cardNum.length(); i++) {
            int digit = Integer.parseInt(cardNum.substring(i, (i + 1)));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        int mod = sum % 10;
        return mod == 0 ? 0 : 10 - mod;
    }

    protected void printAccountInfo() {
        System.out.println("Your card number:\n" + this.getCardNumber());
        System.out.println("Your card PIN:\n" + this.getCardPin());
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardPin() {
        return cardPin;
    }

    public int getCardBalance() {
        return this.cardBalance;
    }

    public int getUserId() {
        return userId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardPin(String cardPin) {
        this.cardPin = cardPin;
    }

    /**
     * setter for import account from DB
     * @param cardBalance new balance
     */
    public void setCardBalance(int cardBalance) {
        this.cardBalance = cardBalance;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void accountTransaction(String direction, int amount) {
        int balance = this.getCardBalance();
        switch (direction) {
            case "+":
                this.setCardBalance(balance + amount);
                break;
            case "-":
                this.setCardBalance(balance - amount);
                break;
        }
    }

    /**
     * Validation checksum of CCard when DoTransfer
     * @param cardNumber target card number
     * @return checksum wrong/right
     */
    public static boolean checkSumValidation(String cardNumber) {
        int[] ints = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            ints[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int anInt : ints) {
            sum += anInt;
        }
        return sum % 10 == 0;
    }
}
