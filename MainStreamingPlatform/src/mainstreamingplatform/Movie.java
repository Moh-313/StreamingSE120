package mainstreamingplatform; 

import java.util.List;
import java.util.ArrayList;
public class Movie extends Content //inherits from content class and represents movie with details and playback behavior
{
    private String director;
    private List<String> cast;
    private String videoPath;

    //constructor to create movie object
    public Movie(String contentId, String title, int duration, String director, List<String> cast, String videoPath)
    {
        super(contentId, title, duration);
        this.director = director;
        this.cast = cast;
        this.videoPath = videoPath;
    }
    //gets the path to the movie file
    public String getVideoPath() 
    {
        return videoPath;
    }

    //sets path
    public void setVideoPath(String videoPath) 
    {
        this.videoPath = videoPath;
    }
    
    //gets director's name
    public String getDirector() 
    {
    return director;
    }

    //sets director
    public void setDirector(String director) 
    {
        this.director = director;
    }

    //gets list of cast members
    public List<String> getCast() 
    {
        return cast;
    }
    
    
    //sets list
    public void setCast(List<String> cast) 
    {
        this.cast = cast;
    }
    
    //simulated play method
    @Override
    public void play()
    {
       System.out.println("Movie is playing.");
    }
    
    //simulated pause method
    @Override
    public void pause()
    {
        System.out.println("Movie is paused.");
    }
}
