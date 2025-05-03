import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.beans.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Main extends Application {
    
    private final StringProperty greeting = new SimpleStringProperty("Hello, enter your name!");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
            Scene scene = new Scene(createContent(), 400, 200);
            primaryStage.setScene(scene);
            primaryStage.show();

    }
    
    private Region createContent(){
        TextField  input =  new TextField("");
        HBox results =  new HBox(new Label("Name:"), input);
        
        results.setSpacing(6);
        results.setAlignment(Pos.CENTER);
        
        Label output = new Label("");
        Button  actionButton = new Button("hello");
        
        actionButton.setOnAction(evt -> output.setText("Hello " + input.getText()));
        
        VBox resultsFinal  = new VBox(20, results, output, actionButton);
        
        resultsFinal.setAlignment(Pos.CENTER);
        
        return resultsFinal;
        
    }
}





