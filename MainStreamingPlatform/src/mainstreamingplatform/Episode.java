package mainstreamingplatform;

public class Episode 
{
   private int episodeNumber;
   private int seasonNumber;
   private String title;
   private int duration;
   
   public Episode(int episodeNumber, int seasonNumber, String title, int duration)
   {
       this.episodeNumber = episodeNumber;
       this.seasonNumber = seasonNumber;
       this.title = title;
       this.duration = duration;
   }
   
   public int getEpisodeNumber()
   {
       return episodeNumber;
   }
   
   public void setEpisodeNumber(int episodeNumber)
   {
       this.episodeNumber = episodeNumber;
   }
   
   
   public int getSeasonNumber()
   {
       return seasonNumber;
   }
   
   public void setSeasonNumber(int seasonNumber)
   {
       this.seasonNumber = seasonNumber;
   }
   
   public String getTitle()
   {
       return title;
   }
   
   public void setTitle(String title)
   {
       this.title = title;
   }
   
   public int getDuration()
   {
       return duration;
   }
   
   public void setDuration(int duration)
   {
       this.duration = duration;
   }
}
