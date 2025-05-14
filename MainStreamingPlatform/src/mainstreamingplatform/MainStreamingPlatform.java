package mainstreamingplatform;

import java.util.Arrays;
import java.util.List;

public class MainStreamingPlatform {

    public static void main(String[] args) {
        
        List<String> cast1 = Arrays.asList("Rachel green", "Chandler Bing"); 
        List<String> cast2 = Arrays.asList("Team Cherry", "Kai Cenat");
         List<String> cast3 = Arrays.asList("Maria 1 ", "Maria 2", "Maria 3");
        
        new Movie(AdminMovieMenuGUI.randomId(), "ori", 156, "Abdulrahman Alassaf", cast1, "Videos/ori.mp4" );
        new Movie(AdminMovieMenuGUI.randomId(), "HollowKnight", 120, "Mohanned attia", cast2, "Videos/HollowKnight.mp4" );
        new Movie(AdminMovieMenuGUI.randomId(), "JurassicPark", 127, "Maria Abbas", cast3, "Videos/JurassicPark.mp4" );
        
        UserGUI.launchUI(args);
        
    }
}

