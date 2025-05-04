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

public class UserGUI extends Application {

    //GUI Datafields
    private Label title, userIdLabel, emailLabel, passwordLabel;
    private TextField idField, emailField, passwordField;
    

    private Button loginButton;
    private User user1;
    
    
    //Method that is called when you launch()
    @Override
    public void start(Stage primaryStage) {
        //App name
        primaryStage.setTitle("Login Page");

        //Creating a grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10); //Horizontal gap between componenets
        grid.setVgap(10); //Vertical gap between components
        grid.setPadding(new Insets(25, 25, 25, 25)); // Padding in order (top,right,bottom,left)
        
        
        //Title at the top
        title = new Label("Welcome! Please Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(title,1,0);
        
        //Make the label for UserID
        userIdLabel = new Label("User ID:");
        userIdLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); //Set the font and size
        grid.add(userIdLabel, 0, 1); // Adds the component to the grid, (column, row)
        
        //Make the textField for UserId
        idField = new TextField();
        grid.add(idField, 1, 1);

        emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        grid.add(emailLabel, 0, 2);

        emailField = new TextField();
        grid.add(emailField, 1, 2);

        passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        grid.add(passwordLabel, 0, 3);

        passwordField = new TextField();
        grid.add(passwordField, 1, 3);
        
        //Make the button to login
        loginButton = new Button("Login");
        loginButton.setPrefWidth(100); // Adjust its width to match the components on top
        grid.add(loginButton, 1, 4);
        
        //setOnAction defines what happens when you clcik the button
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userId = idField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                user1 = new User(userId, email, password); //save the fields to the object of the User
                displayMessage("Login successful, welcome " + userId);

                
            }
        });

        //Make the stage
        Scene loginPage = new Scene(grid, 450, 400);
        primaryStage.setScene(loginPage);
        primaryStage.show(); //call the show method to show the stage
    }
    
    public void displayMessage(String message){
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
