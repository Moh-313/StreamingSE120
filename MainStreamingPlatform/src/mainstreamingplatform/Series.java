package mainstreamingplatform;
import java.util.List;
import java.util.ArrayList;
public class Series extends Content //represents a series, extends content class
{
    private int seasons; //no. of seasons
    private List<Episode> episodes; //list of all episodes
    
    //constryctor initializes basic info and makes empty episode list
    public Series(String contentId, String title, int duration, int seasons)
    {
        super(contentId, title, duration);
        this.seasons = seasons;
        this.episodes = new ArrayList<>();
       
    }
    
    //returns number of seasons
     public int getSeasons() 
    {
    return seasons;
    }

    //sets number of seasons
    public void setSeasons(int seasons) 
    {
        this.seasons = seasons;
    }
    
    
    //returns list of episodes
    public List<Episode> getEpisodes()
    {
        return episodes;
    }
    
    //adds epusode to episodes list
    public void addEpisode(Episode episode)
    {
    episodes.add(episode);
    }
    
    //simulated play method 
    @Override
    public void play() {
        System.out.println("Series is playin");
    }

    //simulated pause button
    @Override
    public void pause() {
        System.out.println("Series is paused");
    }
}
