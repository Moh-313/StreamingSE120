package mainstreamingplatform;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class StreamingServiceGUI extends Application {

    @FXML
    private TextField search;

    @FXML
    private ScrollPane scrollMovies, scrollShows;

    @FXML
    private HBox movieContainer, showContainer;

    @FXML
    private Text greeting;

    private File imageFolder = new File("images/");

    public void start(Stage primaryStage) {

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        User currentUser = UserGUI.getUser();
        if (currentUser != null) {
        greeting.setText("Welcome " + currentUser.getUserId());
        }
    
    createContent();

        createContent();

        search.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.search.setStyle("-fx-background-color: #2A363F; -fx-border-color: lightgreen; -fx-text-fill: white;");
            } else {
                this.search.setStyle("-fx-background-color: #2A363F; -fx-border-color:  #465058; -fx-text-fill: black;");
            }
        });

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue.contains("AdminMovieMenu")) {
                    adminMovieMenuScene();
                } else if (newValue.contains("AdminShowMenu")) {
                    adminShowMenuScene();
                } else {
                    filterContent(newValue);
                }
            } catch (IOException exception) {
                exception.getStackTrace();
            }
        });

    }

    public static void launchGUI(String[] args) {
        launch(args);

    }
    
    // two over loaded methods that creat Cards for there respective containers
    // by making and an empry VBox 
    public VBox createCard(Movie movie) throws NullPointerException {

        VBox card = new VBox();
        card.setAlignment(Pos.CENTER);

        ImageView poster = new ImageView(getImage(movie.getTitle()));
        poster.setFitWidth(222.75);
        poster.setFitHeight(287);

        Text title = new Text(movie.getTitle());
        title.setFill(Color.TRANSPARENT);

        card.getChildren().addAll(poster, title);
        return card;

    }

    public VBox createCard(Series show) throws NullPointerException {
        VBox card = new VBox();
        card.setAlignment(Pos.CENTER);

        ImageView poster = new ImageView(getImage(show.getTitle()));
        poster.setFitWidth(222.75);
        poster.setFitHeight(287);

        Text title = new Text(show.getTitle());
        title.setFill(Color.TRANSPARENT);

        card.getChildren().addAll(poster, title);
        return card;
    }
    
    // clear the containers and the creates content based on the list returned from searchContent(title) 
    // called on initialize in search.textProperty.addlistener 
    private void filterContent(String title) {
        movieContainer.getChildren().clear();
        showContainer.getChildren().clear();

        for (Content content : StreamingService.searchContent(title)) {
            if (content instanceof Movie) {

                VBox movieCard = createCard((Movie) content);
                movieContainer.getChildren().add(movieCard);

            } else {
                VBox showCard = createCard((Series) content);
                showContainer.getChildren().add(showCard);
            }
        }

    }
    
    
    // creates the cards inside the container by using the catalog
    // called on initialize
    private void createContent() {
        try {
            for (Content content : StreamingService.getCatalog()) {
                if (content instanceof Movie) {
                    VBox movieCard = createCard((Movie) content);
                    movieContainer.getChildren().add(movieCard);
                } else {
                    VBox showCard = createCard((Series) content);
                    showContainer.getChildren().add(showCard);
                }
            }
        } catch (NullPointerException exeption) {
            exeption.printStackTrace();

        }
    }
    

    // get the buffered image inside the "Images" folder 
    // checks if it matches "title" and then returns the image
    // called by the two createCard() methods
    private Image getImage(String title) {
        try {
            File[] files = imageFolder.listFiles();
            for (File file : files) {
                if (file.getName().equals(title)) {
                    FileInputStream inputStream = new FileInputStream(file);
                    return new Image(inputStream);

                }
            }

            return null;

        } catch (IOException exception) {
            return null;
        }

    }

    private void adminMovieMenuScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMovieMenuFXML.fxml"));
        Parent movieRoot = loader.load();
        Scene movieScene = new Scene(movieRoot);

        Stage stage = (Stage) search.getScene().getWindow();
        stage.setScene(movieScene);
        stage.setTitle("AdminMenu");
        stage.show();
    }

    private void adminShowMenuScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminShowMenuFXML.fxml"));
        Parent streamingRoot = loader.load();
        Scene streamingScene = new Scene(streamingRoot);

        Stage stage = (Stage) search.getScene().getWindow();
        stage.setScene(streamingScene);
        stage.setTitle("AdminMenu");
        stage.show();

    }
}
