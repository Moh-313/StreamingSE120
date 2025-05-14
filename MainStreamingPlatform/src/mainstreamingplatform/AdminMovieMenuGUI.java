package mainstreamingplatform;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
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
    private Button backButton, addButton; // buttons

    @FXML
    private TextField title, videoFiled, director, cast, duration; // the text field 

    @FXML
    private ImageView image; // where the image is held and displayed

    private final File imageFile = new File("Images/"); // the file the images are saved too

    private final File videoFile = new File("Videos/"); // the file the video are save too

    @FXML
    public void start(Stage primaryStage) {

    }

    @FXML
    public void initialize() {

        // when clicking add it creats a movie object 
        // display a message "Added succesfully"
        // saves the video with the file path entered
        // clear all the container
        addButton.setOnAction(event -> {
            new Movie(randomId(), title.getText(), Integer.parseInt(duration.getText()),
                    director.getText(), splitCast(cast.getText()), videoFile.getName() + "/" + title.getText() + ".mp4" );
            displayMessage("Added succesfully");
            saveVideoToFile(videoFiled.getText());
            clearAll();

        });

        // when the back button is clicked it opens the streaming service
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

    }

    // displays a message with the string passed to it
    public void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Creats a randomId for the movie between 0 - 9000000(excluding) 
    public static String randomId() {
        Random random = new Random();
        return "" + random.nextInt(9000000);
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

    // saves the video to a file names "Videos" 
    // by copying the video from the entered file path and
    // then saves it to  a "Videos" folder in the project 
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

    // Clears all the containers on adding
    public void clearAll() {
        title.clear();
        director.clear();
        cast.clear();
        videoFiled.clear();
        image.setImage(null);
        duration.clear();

    }

    // loads the streaming service 
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

    // splits the cast by the , seprator 
    public List<String> splitCast(String cast) {
        return Arrays.asList(cast.split(","));
    }

}
