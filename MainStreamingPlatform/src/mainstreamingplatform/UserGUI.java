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

public class UserGUI extends Application {

    //GUI Datafields
    @FXML
    private TextField userIdField, emailField;

    @FXML
    private PasswordField passwordField; //Allows the password to be hidden when entered
    
    @FXML
    private Button loginButton;//Button
    
    private User currentUser; //Create an object of the User class

    //Method that is called when you launch()
    @Override
    public void start(Stage loginStage) {
        
        //Needs to be in a try catch block incase the FXML file is'nt found
        try {
            Parent loginmenu = FXMLLoader.load(getClass().getResource("LoginFXML.fxml")); //Opens the FXML scenbuilder file
            
            //Create the scene (main page)
            Scene loginScene = new Scene(loginmenu);
            loginStage.setTitle("Login Page");
            loginStage.setScene(loginScene);
            loginStage.show();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void initialize(){
        
        //Decalres what happens when you click the button
        loginButton.setOnAction(event ->{
        
            //Gets what the user inputs and saves it
        String UserId = userIdField.getText();
        String Email = emailField.getText();
        String Password = passwordField.getText();
        
        this.currentUser = new User(UserId, Email, Password); //Create an object with what the user enetered
        
        try {
            
            FXMLLoader subscriptionLoader = new FXMLLoader(getClass().getResource("SubscriptionPage.fxml"));
            Parent subscriptionParent = subscriptionLoader.load();
            SubscriptionClass subscriptionClass = subscriptionLoader.getController();
            
            if (subscriptionClass == null) {
            displayMessage("Error: Could not get controller for subscription page");
            return;
        }
            subscriptionClass.setUser(currentUser);
            
            Stage subscriptionStage = (Stage) loginButton.getScene().getWindow();
            Scene subscriptionScene = new Scene(subscriptionParent);
            subscriptionStage.setScene(subscriptionScene);
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
        //displayMessage("hello");
        
    });
        
        //Glow effect when u click the textfield
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) { 
            emailField.setStyle("-fx-background-color: #2A363F; -fx-border-color: lightgreen; -fx-text-fill: white;");
        } else { 
            emailField.setStyle("-fx-background-color: #2A363F; -fx-border-color:  #465058; -fx-text-fill: black;");
        }
    });
        
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
    
    //Setting up the pop up message when you login
    public void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public User getUser() {
        return currentUser;
    }

    //Method to launch the UI
    public static void launchUI(String[] args) {
        launch(args);
    }
}
