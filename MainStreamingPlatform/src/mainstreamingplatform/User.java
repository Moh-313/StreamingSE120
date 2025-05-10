package mainstreamingplatform;


import java.io.*;
import java.util.*;

public class User {

    private String userId;
    private String email;
    private String password;
    private Boolean loggedIn;
    
    //Creates an empty hash set which stores the data of the users who log in, this is only while the file runs however.
    public static Set<String> usersLoggedIn = new HashSet<>(); 
    //Creates a file in the package that stores the users, which solves the issue of the hash set being temporary
    public static final String usersFile = "usersFile.txt";
    
    //Parameterized Constructor            
    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
    
    //Set and Get methods    
    public void setUserId(String userId){
        this.userId = userId;
    }
    
    public String getUserId(){
        return userId;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }

    //Tracks    
    public boolean loggedIn() {
        
        //User loggedUser = findUser(this.userId,this.email,this.password);
        loggedIn = true;
        return loggedIn;
    }
    
    public void logout() {

    }

}
