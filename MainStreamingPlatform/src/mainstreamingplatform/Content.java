
package mainstreamingplatform;

public abstract class Content {
    
    private final String contentId;
    private final String title;
    private final int duration;
    
    
        Content(String contentId, String title, int duration){
            this.contentId = contentId;
            this.title = title;
            this.duration = duration;
        }
    
    
    public void play(){
        
    }
    
    public void pause(){
        
    }
    

    public String getContentId() {
        return contentId;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }
   
    
}
