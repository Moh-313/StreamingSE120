package mainstreamingplatform;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.Random;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javax.imageio.ImageIO;

public class AdminMovieMenuGUI extends Application {

    @FXML
    private Button backButton, addButton;

    @FXML
    private TextField title, videoFiled, director, cast;

    @FXML
    private ImageView image;

    private final File imageFile = new File("Images/");

    private final File videoFile = new File("Videos/");

    @FXML
    public void start(Stage primaryStage) {

    }

    @FXML
    public void initialize() {

        addButton.setOnAction(event -> {
            new Movie(randomId(), title.getText(), 0, director.getText(), cast.getText());
            displayMessage("Added succesfully");
            saveVideoToFile(videoFiled.getText());
            clearAll();

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

    }

    public void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public String randomId() {
        Random random = new Random();
        return "" + random.nextInt(9000000);
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

    public void saveVideoToFile(String videoPath) {

        try {

            File video = new File(videoPath);
            File namedVideo = new File(videoFile, video.getName());
            Files.copy(video.toPath(), namedVideo.toPath());
            System.out.println("saved");

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void clearAll() {
        title.clear();
        director.clear();
        cast.clear();
        videoFiled.clear();
        image.setImage(null);

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
