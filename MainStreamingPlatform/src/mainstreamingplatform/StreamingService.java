package mainstreamingplatform;
import java.util.ArrayList;
import java.util.List;

public class StreamingService {

    //Stores content.
    private static List<Content> catalog =  new ArrayList<Content>();

    //Adds content to the catalog.
    public static void addContentToCatalog(Content content){
        catalog.add(content);
    }


    //Searches for the title in the catalog,
    //If it contains part of the title adds it to a new list, and returns the new list after searching.
    public static ArrayList<Content> searchContent(String title){
        title = title.toLowerCase();
        ArrayList<Content> searchedContent =  new ArrayList<Content>();
        for(Content content: catalog){
            if(content.getTitle().toLowerCase().contains(title)){
                searchedContent.add(content);
            }
        }
        return searchedContent;
    }
    
   


    public static void streamContent(String contentId){
        

    }

    //Getter for catalog.
    public static List<Content> getCatalog(){
        return catalog;
    }
    
    
   
    
        

}
