package mainstreamingplatform;

public class User {

    private String userId;
    private String email;
    private String password;

    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
    
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
            
    public boolean login() {
        boolean loggedIn = true;
        return loggedIn;
    }

    public void logout() {

    }

}
