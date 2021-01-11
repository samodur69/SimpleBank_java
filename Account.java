package banking;
import java.util.Random;

public class Account {
    private String cardNumber;
    private String cardPin;
    private long cardBalance;

    /**
     * Initialization - set balance 0, print account info (card, pin)
     */
    public Account () {
        this.cardNumber = generateCardNumber();
        this.cardPin = generatePinCode();
        this.cardBalance = 0;
        printAccountInfo();
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

    public long getCardBalance() {
        return this.cardBalance;
    }

//    public void setCardBalance(long cardBalance) {
//        this.cardBalance = cardBalance;
//    }
}
