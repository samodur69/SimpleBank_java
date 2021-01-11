package banking;
import java.util.Random;

public class Account {
//    final static long seed = System.currentTimeMillis();
    private String cardNumber;
    private String cardPin;
    private long cardBalance = 0;

    public Account () {
        this.cardNumber = generateCardNumber();
        this.cardPin = generatePinCode();
        printAccountInfo();
    }

    private static String generateCardNumber() {
        /**
          function to generate new account number.
          @return String number
         */
        Random random = new Random();
        StringBuilder cardNum = new StringBuilder("400000");
        for (int i = 0; i < 9; i++) {
            cardNum.append(random.nextInt(10));
        }
        cardNum.append(checkSumGenerator(cardNum.toString()));
        return cardNum.toString();
    }

    protected static String generatePinCode() {
        /**
         * function for generation Pin
         */
        Random random = new Random();
        StringBuilder pinCode = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            pinCode.append(random.nextInt(10));
        }
        return pinCode.toString();
    }

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

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardPin() {
        return cardPin;
    }

    public void setCardPin(String cardPin) {
        this.cardPin = cardPin;
    }

    public long getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(long cardBalance) {
        this.cardBalance = cardBalance;
    }
}
