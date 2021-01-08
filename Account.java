package banking;
import java.util.Random;

public class Account {
    private String cardNumber = "400000";
    private String cardPin = "";
    private long cardBalance = 0;

    private void generateCardNumber() {
        /**
          function to generate new account number.
          @return String number
         */
        Random random = new Random();
        System.out.println("\nGeneration card number");
        for (int i = 0; i < 10; i++) {
            this.cardNumber += random.nextInt(10);
        }
        System.out.println(this.cardNumber + "\n");
//        return this.cardNumber;
    }

    private void generatePinCode() {
        /**
         * function for generation Pin
         */
        Random random = new Random();
        System.out.println("\nGeneration Pin Code");
        for (int i = 0; i < 4; i++) {
            this.cardPin += random.nextInt(10);
        }
        System.out.println(this.cardPin + "\n");
//        return this.cardPin;
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
