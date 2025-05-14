package mainstreamingplatform;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

// this file is not finished and lacks full functionality
public class AdminShowMenuGUI extends Application {

    @FXML
    private Button backButton, addButton, episodesButton; // the buttons 

    @FXML
    private TextField title, seasons, director, cast, genericTextField; // the text fields

    @FXML
    private ImageView image; // where the image is held and displayed

    @FXML
    private VBox seasonsNumber; // VBox that the seasons episode number container are created in

    private final File imageFile = new File("Images/");  // the file the images are saved too

    @FXML
    public void start(Stage primaryStage) {

    }

    @FXML
    public void initialize() {
        // TODO
        episodesButton.setOnAction(event -> {
            
            
        });

        // loads the streaming service when pressed
        backButton.setOnAction(event -> {
            streamingServiceScene();

        });
        
        
        // creats a event when dragged over
        // checks if the thing draged over is a file 
        // and is not dragged from the imageviewer container
        // and creats a copy if both conditions are meet
        image.setOnDragOver(event -> {
            if (event.getGestureSource() != image
                    && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

          
        // creats a event when the file is droped
        // gets the drag board from the event
        // checks if it has files if true gets the first file convert it to image 
        // and then sets the image for the image viewer then saves the image
        image.setOnDragDropped(event -> {
            Dragboard board = event.getDragboard();

            if (board.hasFiles()) {
                File file = board.getFiles().get(0);
                Image image = new Image(file.toURI().toString());
                this.image.setImage(image);

                saveImageToFile(image, imageFile);

            }
        });
        
        // clears the seasonsNumber container 
        // checks the number entered in the text file
        // converts it to a int from String
        // then uses a for loop to create and add containers to seasonNumber
        seasons.textProperty().addListener((observable, oldValue, newValue) -> {
            seasonsNumber.getChildren().clear();

            try {
                for (int i = 0; i < Integer.parseInt(newValue); i++) {
                    episodesButton.setVisible(true);
                    seasonsNumber.getChildren().add(createContainer(i));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });

    }
    
    // saves the image inside a "Images" folder in the project 
    // By making a buffered image and then writing it inside a "Images" file with the title name
    public void saveImageToFile(Image image, File file) {
        try {
            File movieFile = new File(imageFile, title.getText());
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bImage, "png", movieFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // creats a container using the properties of the generic container
    // appends the number to the end of the text inside it 
    public TextField createContainer(int number) {

        TextField textField = new TextField();
        textField.setPromptText("number of episodes for S" + (number + 1));
        textField.setPrefWidth(50);
        textField.setPrefHeight(150);
        textField.setStyle(genericTextField.getStyle());
        textField.setFont(genericTextField.getFont());
        textField.setEditable(genericTextField.isEditable());
        textField.setDisable(genericTextField.isDisable());
        textField.setVisible(true);

        return textField;
    }
    // Loads the streaming service menu
    public void streamingServiceScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StreamingServiceFXML.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
