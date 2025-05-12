
package mainstreamingplatform;

import java.util.ArrayList;
import java.util.List;

public class Movie extends Content{
    
    String director;
    List<String> cast = new ArrayList<String>();

    Movie(String contentId, String title, int duration, String director, String cast){
        super(contentId, title, duration);
        this.director = director;
        this.cast.add(cast);
    }
    
    
    
}
