package mainstreamingplatform;

public class Episode //represents an episode 
{
    //attributes for episode
   private int episodeNumber;
   private int seasonNumber;
   private String title;
   private int duration;
   
   //constructor to initialize eposide details
   public Episode(int episodeNumber, int seasonNumber, String title, int duration)
   {
       this.episodeNumber = episodeNumber;
       this.seasonNumber = seasonNumber;
       this.title = title;
       this.duration = duration;
   }
   
   //returns the episode number
   public int getEpisodeNumber()
   {
       return episodeNumber;
   }
   
   //sets episode number
   public void setEpisodeNumber(int episodeNumber)
   {
       this.episodeNumber = episodeNumber;
   }
   
   //returns season number
   public int getSeasonNumber()
   {
       return seasonNumber;
   }
   
   //sets season number
   public void setSeasonNumber(int seasonNumber)
   {
       this.seasonNumber = seasonNumber;
   }
   
   //returns title
   public String getTitle()
   {
       return title;
   }
   
   //sets title of the episode
   public void setTitle(String title)
   {
       this.title = title;
   }
   
   //returns duration
   public int getDuration()
   {
       return duration;
   }
   
   //sets duration
   public void setDuration(int duration)
   {
       this.duration = duration;
   }
}
