package banking;
import java.util.Random;

public class Account {
    private String cardNumber;
    private String cardPin;
    private long cardBalance = 0;

    public Account () {
        this.cardNumber = generateCardNumber();
        this.cardPin = generatePinCode();
        printAccountInfo();
    }

    private static String generateCardNumber() {  // peredelat na vozvrat
        /**
          function to generate new account number.
          @return String number
         */
        Random random = new Random();
        String cardNum = "400000";
        for (int i = 0; i < 10; i++) {
            cardNum += random.nextInt(10);
        }
        return cardNum;
    }

    protected static String generatePinCode() {
        /**
         * function for generation Pin
         */
        Random random = new Random();
        String pinCode = "";
        for (int i = 0; i < 4; i++) {
            pinCode += random.nextInt(10);
        }
        return pinCode;
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
