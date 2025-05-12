package mainstreamingplatform;


public class CreditCardPayment implements Payment{
    private String cardNumber;
    private String expiryDate;

    public CreditCardPayment(String cardNumber, String expiryDate) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    // setter for cardNumber
    public String getCardNumber() {
        return cardNumber;
    }

    // setter for cardNumber
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // getter for expiryDate
    public String getExpiryDate() {
        return expiryDate;
    }

    // setter for expiryDate
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount + 
                         " with card expiring on " + expiryDate);
        return true;
    }
}
