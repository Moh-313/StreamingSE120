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

public class AdminShowMenuGUI extends Application {

    @FXML
    private Button backButton, addButton, episodesButton;

    @FXML
    private TextField title, seasons, director, cast, genericTextField;

    @FXML
    private ImageView image;

    @FXML
    private VBox seasonsNumber;

    private final File imageFile = new File("Images/");

    @FXML
    public void start(Stage primaryStage) {

    }

    @FXML
    public void initialize() {
        
        episodesButton.setOnAction(event -> {
            
            
        });

        backButton.setOnAction(event -> {
            streamingServiceScene();

        });

        image.setOnDragOver(event -> {
            if (event.getGestureSource() != image
                    && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        image.setOnDragDropped(event -> {
            Dragboard board = event.getDragboard();

            if (board.hasFiles()) {
                File file = board.getFiles().get(0);
                Image image = new Image(file.toURI().toString());
                this.image.setImage(image);

                saveImageToFile(image, imageFile);

            }
        });

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

    public void saveImageToFile(Image image, File file) {
        try {
            File movieFile = new File(imageFile, title.getText());
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bImage, "png", movieFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
