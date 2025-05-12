package mainstreamingplatform;

/**
 *
 * @author mohan
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SubscriptionClass2 implements Initializable {
    @FXML
    private Button basicButton, moderateButton, advancedButton;//Buttons
    
    private static User currentUserSC;

    private static String chosenPlan;
    
    public void initialize(URL url, ResourceBundle rb){

        basicButton.setOnAction(event -> {
            chosenPlan = Subscriber.basicPlan;
            paymentPage(4.99);

        });
        moderateButton.setOnAction(event -> {
            chosenPlan = Subscriber.moderatePlan;
            paymentPage(7.99);

        });
        advancedButton.setOnAction(event -> {
            chosenPlan = Subscriber.advancedPlan;
            paymentPage(9.99);

        });
    }
    
    private void paymentPage(double price){
         try {
            FXMLLoader paymentLoad = new FXMLLoader(getClass().getResource("PaymentFXML.fxml"));
            Parent paymentRoot = paymentLoad.load();
            
            // Get the controller for the FXML page and set the price
            PaymentGUI paymentController = paymentLoad.getController();
            paymentController.setAmount(price);
            paymentController.setUserAndPlan(currentUserSC, chosenPlan);
            
            // Navigate to payment page
            Scene paymentScene = new Scene(paymentRoot);
            Stage stage = (Stage) basicButton.getScene().getWindow();
            stage.setScene(paymentScene);
            stage.setTitle("Payment");
            stage.show();
        } catch (Exception e) {
            displayMessage("Error navigating to payment page: " + e.getMessage());
        }
    }
    
    public void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public void setUser(User user){
        currentUserSC = user;
    }
    
    public static User getCurrentUser() {
        return currentUserSC;
    }
    
    public static String getSelectedPlan() {
        return chosenPlan;
    }
}

