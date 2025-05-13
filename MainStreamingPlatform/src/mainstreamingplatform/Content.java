package mainstreamingplatform;
public abstract class Content extends StreamingService {

    private final String contentId;
    private final String title;
    private final int duration;

    //constructor for content called when creating a movie/Series
    Content(String contentId, String title, int duration){
        this.contentId = contentId;
        this.title = title;
        this.duration = duration;
        StreamingService.addContentToCatalog(this);

    }

    // prints playing content
    public void play(){
        System.out.println("playing content");

    }
    // prints pasuging content
    public void pause(){
        System.out.println("pausing content");

    }

    //Getters for contentId, title, duration

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
