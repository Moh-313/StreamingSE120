package mainstreamingplatform;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class SubscriptionClass implements Initializable {
    
    private User currentUserSC;
          
    public void initialize(URL url, ResourceBundle rb){

    }
    
    public void setUser(User currentUserSC){
        this.currentUserSC = currentUserSC;
    }
}
