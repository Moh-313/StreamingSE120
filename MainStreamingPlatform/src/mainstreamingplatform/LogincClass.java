package mainstreamingplatform;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
/**
 *
 * @author mohan
 */
public class LogincClass implements Initializable{
    //GUI Datafields
    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField passwordField; //Allows the password to be hidden when entered
    
    @FXML
    private Button logInButton, signinbutton;//Buttons
    
    private User currentUserL;
    
    @FXML
    public void initialize(URL url, ResourceBundle rb){
        
        //Decalres what happens when you click the button
        logInButton.setOnAction(event ->{
                
                if(userIdField.getText() == null || passwordField.getText() == null){
                    displayMessage("Please fill in both your UserId and Password.");
                }
                else{
                String userId = userIdField.getText();
                String password = passwordField.getText();
                }
               
            try {
                //Loads the new FXML page and creates an instance so you can access the controller
                FXMLLoader subscriptionLoader = new FXMLLoader(getClass().getResource("SubscriptionPage.fxml"));
                Parent subscriptionParent = subscriptionLoader.load();
            
                //Find the controlller for the FXML file (SubscriptionClass)
                SubscriptionClass subscriptionClass = subscriptionLoader.getController();

            
                //Create a new stage and scene, pass the parent with the FXML file to the scene, and then pass the scene to the stage
                Stage subscriptionStage = (Stage) logInButton.getScene().getWindow();
                Scene subscriptionScene = new Scene(subscriptionParent);
                subscriptionStage.setScene(subscriptionScene);
            } 
        
            catch (Exception e) {
            ;
            }
        //displayMessage("hello");
        
    });
        
        signinbutton.setOnAction(event ->{
        try {
            //Loads the new FXML page and creates an instance so you can access the controller
            FXMLLoader signInLoader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
            Parent signInParent = signInLoader.load();

            
            //Create a new stage and scene, pass the parent with the FXML file to the scene, and then pass the scene to the stage
            Stage signInStage = (Stage) signinbutton.getScene().getWindow();
            Scene signInScene = new Scene(signInParent);
            signInStage.setScene(signInScene);
        } 
        
        catch (Exception e) {
            ;
        }
        });
        
    //Glow effect when u click the textfield   
        
    userIdField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) { 
            userIdField.setStyle("-fx-background-color: #2A363F; -fx-border-color: lightgreen; -fx-text-fill: white;");
        } else { 
            userIdField.setStyle("-fx-background-color: #2A363F; -fx-border-color:  #465058; -fx-text-fill: black;");
        }
    });
    
    passwordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) { 
            passwordField.setStyle("-fx-background-color: #2A363F; -fx-border-color: lightgreen; -fx-text-fill: white;");
        } else { 
            passwordField.setStyle("-fx-background-color: #2A363F; -fx-border-color:  #465058; -fx-text-fill: black;");
        }
    });
    
    //Makes it so if doesn't hover a textfield automatically on start, and waits for you to click it
    Platform.runLater(() -> {
        userIdField.getParent().requestFocus();
    });
    
}
    public void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
