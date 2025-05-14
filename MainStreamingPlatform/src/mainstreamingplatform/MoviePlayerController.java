package mainstreamingplatform;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.util.List;

public class MoviePlayerController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label durationLabel;

    @FXML
    private Label directorLabel;

    @FXML
    private Label castLabel;

    @FXML
    private MediaView mediaView;

    @FXML
    private Button playButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button exitButton;

    @FXML
    private VBox infoBox;

    @FXML
    private Pane logoPane;

    private MediaPlayer mediaPlayer;

    public void setMovie(Movie movie) {
        titleLabel.setText(movie.getTitle().toUpperCase());
        durationLabel.setText("Duration: " + movie.getDuration() + " minutes");
        directorLabel.setText("Director: " + movie.getDirector());
        castLabel.setText("Cast: " + String.join(", ", movie.getCast()));

        String videoPath = new File(movie.getVideoPath()).toURI().toString();
        Media media = new Media(videoPath);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        pauseButton.setVisible(false);
        exitButton.setVisible(false);
        mediaView.setFitWidth(600);
        mediaView.setFitHeight(300);

        applyIconButtonStyle(playButton);
        applyIconButtonStyle(pauseButton);
        applyIconButtonStyle(exitButton);
    }

    private void applyIconButtonStyle(Button button) {
        button.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;" +
            "-fx-padding: 0;" +
            "-fx-cursor: hand;" +
            "-fx-opacity: 1.0;"
        );

        button.setOnMouseEntered(e -> button.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;" +
            "-fx-padding: 0;" +
            "-fx-cursor: hand;" +
            "-fx-opacity: 0.8;" +
            "-fx-effect: dropshadow(gaussian, rgba(255,255,255,0.3), 8, 0.5, 0, 0);"
        ));

        button.setOnMouseExited(e -> button.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;" +
            "-fx-padding: 0;" +
            "-fx-cursor: hand;" +
            "-fx-opacity: 1.0;"
        ));
    }

    @FXML
    private void handlePlay() {
        if (mediaPlayer != null) {
            MediaPlayer.Status status = mediaPlayer.getStatus();

            if (status == MediaPlayer.Status.PAUSED) {
                mediaPlayer.play();
                playButton.setVisible(false);
                pauseButton.setVisible(true);
                return;
            }

            mediaPlayer.play();
            infoBox.setVisible(false);
            titleLabel.setVisible(false);
            logoPane.setVisible(false);
            playButton.setVisible(false);

            Platform.runLater(() -> {
                double width = mediaView.getParent().getLayoutBounds().getWidth();
                double height = mediaView.getParent().getLayoutBounds().getHeight();
                mediaView.setFitWidth(width);
                mediaView.setFitHeight(height);
            });

            pauseButton.setVisible(true);
            exitButton.setVisible(true);
        }
    }

    @FXML
    private void handlePause() {
        if (mediaPlayer != null) {
            MediaPlayer.Status status = mediaPlayer.getStatus();

            if (status == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
                playButton.setVisible(true);
                pauseButton.setVisible(false);
            } else if (status == MediaPlayer.Status.PAUSED) {
                mediaPlayer.play();
                playButton.setVisible(false);
                pauseButton.setVisible(true);
            }
        }
    }

    @FXML
    private void handleExit() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        mediaView.fitWidthProperty().unbind();
        mediaView.fitHeightProperty().unbind();
        mediaView.setFitWidth(600);
        mediaView.setFitHeight(300);

        infoBox.setVisible(true);
        titleLabel.setVisible(true);
        logoPane.setVisible(true);
        playButton.setVisible(true);
        pauseButton.setVisible(false);
        exitButton.setVisible(false);
    }
}
