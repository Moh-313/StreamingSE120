package mainstreamingplatform;

public class PayPalPayment implements Payment {
     private String accountEmail;

    public PayPalPayment(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    // getter for accountEmail
    public String getAccountEmail() {
        return accountEmail;
    }

    // setter for accountEmail
    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount + 
                         " with account " + accountEmail);
        return true;
    }
}
