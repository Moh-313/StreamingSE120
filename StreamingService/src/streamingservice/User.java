package streamingservice;

public class User {
   private String userId;
   private String email;
   private String password;
   
   public User(String userId, String email, String password){
       this.userId = userId;
       this.email = email;
       this.password = password;
   }
   
   public boolean login(){
       boolean loggedIn = false;
       return loggedIn;
   }
   
   public void logout(){
       
   }
   
}
