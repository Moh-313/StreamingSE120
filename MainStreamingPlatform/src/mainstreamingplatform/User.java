package mainstreamingplatform;

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class User {

    private String userId;
    private String email;
    private String password;
    private Boolean loggedIn;

    //Creates a file in the package that stores the users
    public static final String usersFile = "usersFile.txt";

    //No-argument Constructo
    public User() {

    }

    //Parameterized Constructor            
    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    //Set and Get methods    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    //Basically getLoggedIn()   
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    
    //Method to save users
    public void saveUser() {

        Boolean userFound = false;
        String line;

        try {
            //loads the arrayList
            List<User> usersSignedIn = loadUsers();

            //check if the userID or email matches the ones in any object in the list
            for (User user : usersSignedIn) {
                if (user.getUserId().equals(this.userId) || user.getEmail().equals(this.email)) {
                    userFound = true;
                }
            }
            if (userFound == false) {

                //write/add the user into the file, true refers to append mode, allowing text to be added at the end of the file instead of overwriting
                FileWriter userWriter = new FileWriter(usersFile, true);
                //BufferedWriter improves FileWriter by allowing it to collect mutliple characters and appending it
                BufferedWriter userBufferedWriter = new BufferedWriter(userWriter);

                userBufferedWriter.write(this.userId + "," + this.email + "," + this.password);
                userBufferedWriter.newLine();
                userBufferedWriter.close();

            }
        } catch (IOException read) {
            System.out.print("error1");
        }
        
    }  

    public static List<User> loadUsers() {
        //creates the arrayList
        List<User> users = new ArrayList<>();
        
        
        try {
            String line;
            
            //create an object of the file so we can use operations
            File fileObject = new File(usersFile);

            if (!fileObject.exists()) {
                fileObject.createNewFile();
                return users; //return the empty list of users
            }

            BufferedReader userBufferedReader = new BufferedReader(new FileReader(fileObject
            
            ));
            userBufferedReader.close();
            
            //set the string line to hold each line it the file, then before readline is null
            while ((line = userBufferedReader.readLine()) != null) {
                //we seperate the data by the , we used earlier to differentiate the data field
                String[] lbl = line.split(",");
                //checks to see if the object in the array has 3 indexes already, if so, we make a new object and add the data fields to it
                if (lbl.length >= 3) {
                    User user = new User();
                    user.setUserId(lbl[0]);
                    user.setEmail(lbl[1]);
                    user.setPassword(lbl[2]);
                    users.add(user);
                }
            }

        } catch (IOException lbl) {
            System.out.print("lbl error");
        }
        //returns the now popilated list
        return users;
    }
    
    public boolean login(String userId,String password){
        List<User> users = loadUsers();
        
        for(User user : users){
            if(user.getUserId().equals(userId) && user.getPassword().equals(password)){
                this.loggedIn = true;
                this.userId = userId;
                this.email = email;
                this.password = password;
                return true;
            }
        }
        return false;
    }

}

