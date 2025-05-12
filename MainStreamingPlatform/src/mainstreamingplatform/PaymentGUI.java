package mainstreamingplatform;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*; // for JavaFx controls like buttons RadioButtons etc
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentGUI implements Initializable {

    // payment options: credit card or PayPal
    @FXML
    private RadioButton creditCardRadio;
    @FXML
    private RadioButton paypalRadio;

    // where users type their payment details
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField expiryField;
    @FXML
    private TextField paypalEmailField;

    // shows price and if payment worked
    @FXML
    private Label amountLabel;
    @FXML
    private Label resultLabel;

    // buttons
    @FXML
    private Button payButton;
    @FXML
    private Button returnHomeButton;

    //  default price 0 (will change in setAmount based on Subscription plan choice) 
    private double price = 0;

    //  runs when the payment screen loads 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // makes sure only one payment method can be picked from thr RadioButtons
        // ToggleGroup class(from javafx.scene.control) 
        ToggleGroup paymentMethodGroup = new ToggleGroup();
        creditCardRadio.setToggleGroup(paymentMethodGroup);// setToggleGroup()method is from the RadioButton class in scene.control
        paypalRadio.setToggleGroup(paymentMethodGroup);

        // starts with credit card selected by default
        creditCardRadio.setSelected(true); // method from RadioButton

        togglePaymentFields();// shows the right fields for credit card 

        payButton.setOnAction(e -> handlePayment()); // when pay button is clicked call handlePayment 

        // makes the radio buttons update the fields when switched
        creditCardRadio.setOnAction(e -> togglePaymentFields());
        paypalRadio.setOnAction(e -> togglePaymentFields());
        // whenever a radio button is clicked, it calls togglePaymentFields() again

    }

    // shows credit card fields or PayPal field based on choice
    private void togglePaymentFields() {
        boolean isCreditCard = creditCardRadio.isSelected(); // returns true of credidCardRadio is active
        // if credit card is picked (which in default is), show those fields and hide PayPal
        cardNumberField.setVisible(isCreditCard);
        expiryField.setVisible(isCreditCard);
        // if PayPal is picked show the email field and hide the creditCard fields
        paypalEmailField.setVisible(!isCreditCard);
    }

    // runs when u click Pay now button
    @FXML
    private void handlePayment() {
        try {
            //calls validateAndProcessPayment
            boolean success = validateAndProcessPayment();
            // shows if payment worked ot not 
            resultLabel.setText(success ? "Payment successful!" : "Payment failed");// Label class
            // set green for success red for failure
            resultLabel.setStyle(success ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
        } catch (IllegalArgumentException e) {

            resultLabel.setText(e.getMessage());
            resultLabel.setStyle("-fx-text-fill: red;");
        }
    }

    // handles the payment method that u chose
    private boolean validateAndProcessPayment() {
        if (creditCardRadio.isSelected()) {
            validateCreditCard(); // if creditcard, call validateCreditCard to validate each field
            return processCreditCardPayment();
        } else {
            validatePayPal(); // if PayPal, call validatePayPal to validate email 
            return processPayPalPayment();
        }
    }

    // called if creditcard is selected
    private void validateCreditCard() {
        String cardNumber = cardNumberField.getText();
        String expiry = expiryField.getText();

        // validate for every possible error
        if (cardNumber.isEmpty() || expiry.isEmpty()) {
            throw new IllegalArgumentException("All credit card fields are required");
        }
        //  matches is from the String class
        if (!cardNumber.matches("\\d{16}")) { // \\d means "digit" and {16} means "exactly 16 numbers"
            throw new IllegalArgumentException("Card number must be 16 digits");
        }

        if (!expiry.matches("(0[1-9]|1[0-2])/\\d{2}")) { // regular expressions
            throw new IllegalArgumentException("Expiry must be MM/YY format");
        }
    }

    // checks if the PayPal email looks real
    private void validatePayPal() {
        String email = paypalEmailField.getText();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("PayPal email is required");
        }
        //  email check: needs @ and .something
        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // regular expressions
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    // called after succesdull creditcard validation
    private boolean processCreditCardPayment() {
        CreditCardPayment paymentProcessor = new CreditCardPayment(
                cardNumberField.getText(),
                expiryField.getText()
        );
        return paymentProcessor.processPayment(price);
    }

    // called after succesfull paypal validation
    private boolean processPayPalPayment() {
        
        PayPalPayment paymentProcessor = new PayPalPayment(
                paypalEmailField.getText()
        );
        return paymentProcessor.processPayment(price);
    }

    // updates the amount 
    public void setAmount(double price) {
        this.price = price;
        amountLabel.setText(String.format("Amount: $%.2f", price));
    }
}
