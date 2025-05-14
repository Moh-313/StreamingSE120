package mainstreamingplatform;
import java.util.List;
import java.util.ArrayList;
public class Series extends Content
{
    private int seasons;
    private List<Episode> episodes;
    
    public Series(String contentId, String title, int duration, int seasons)
    {
        super(contentId, title, duration);
        this.seasons = seasons;
        this.episodes = new ArrayList<>();
       
    }
     public int getSeasons() 
    {
    return seasons;
    }

    public void setSeasons(int seasons) 
    {
        this.seasons = seasons;
    }
    
    public List<Episode> getEpisodes()
    {
        return episodes;
    }
    
    public void addEpisode(Episode episode)
    {
    episodes.add(episode);
    }
    
    @Override
    public void play() {
        System.out.println("Series is playin");
    }

    @Override
    public void pause() {
        System.out.println("Series is paused");
    }
}
