package mainstreamingplatform; 

import java.util.List;
import java.util.ArrayList;
public class Movie extends Content
{
    private String director;
    private List<String> cast;
    private String videoPath;

    public Movie(String contentId, String title, int duration, String director, List<String> cast, String videoPath)
    {
        super(contentId, title, duration);
        this.director = director;
        this.cast = cast;
        this.videoPath = videoPath;
    }

    public String getVideoPath() 
    {
        return videoPath;
    }

    public void setVideoPath(String videoPath) 
    {
        this.videoPath = videoPath;
    }

    public String getDirector() 
    {
    return director;
    }

    public void setDirector(String director) 
    {
        this.director = director;
    }

    public List<String> getCast() 
    {
        return cast;
    }

    public void setCast(List<String> cast) 
    {
        this.cast = cast;
    }
    
    @Override
    public void play()
    {
       System.out.println("Movie is playing.");
    }
    @Override
    public void pause()
    {
        System.out.println("Movie is paused.");
    }
}
