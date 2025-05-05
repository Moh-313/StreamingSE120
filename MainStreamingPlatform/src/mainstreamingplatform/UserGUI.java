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
    
    private User user1; //Create and object of the user class

    //Method that is called when you launch()
    @Override
    public void start(Stage primaryStage) {

        try {
            Parent loginmenu = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            //App name
            Scene scene = new Scene(loginmenu);
            primaryStage.setTitle("Login Page");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void initialize(){
        
        loginButton.setOnAction(event ->{
            
        String UserId = userIdField.getText();
        String Email = emailField.getText();
        String Password = passwordField.getText();
        
        this.user1 = new User(UserId, Email, Password);
        displayMessage("Login successful, Welcome " + UserId);
    });
        
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) { // Field is focused/clicked
            emailField.setStyle("-fx-background-color: #2A363F; -fx-border-color: lightgreen; -fx-text-fill: white;");
        } else { // Field is unfocused/unclicked
            emailField.setStyle("-fx-background-color: #2A363F; -fx-border-color:  #465058; -fx-text-fill: black;");
        }
    });
        
    userIdField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) { // Field is focused/clicked
            userIdField.setStyle("-fx-background-color: #2A363F; -fx-border-color: lightgreen; -fx-text-fill: white;");
        } else { // Field is unfocused/unclicked
            userIdField.setStyle("-fx-background-color: #2A363F; -fx-border-color:  #465058; -fx-text-fill: black;");
        }
    });
    
    passwordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) { // Field is focused/clicked
            passwordField.setStyle("-fx-background-color: #2A363F; -fx-border-color: lightgreen; -fx-text-fill: white;");
        } else { // Field is unfocused/unclicked
            passwordField.setStyle("-fx-background-color: #2A363F; -fx-border-color:  #465058; -fx-text-fill: black;");
        }
    });
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

    public User getUser() {
        return user1;
    }

    //Method to launch the UI
    public static void launchUI(String[] args) {
        launch(args);
    }
}
