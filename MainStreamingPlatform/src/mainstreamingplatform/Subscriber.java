package mainstreamingplatform;

/**
 *
 * @author mohan
 */
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Subscriber extends User {

    //Data fields
    private String subscriptionType;
    private Date subscriptionEnd;
    SimpleDateFormat endDate = new SimpleDateFormat("MMMM d, yyyy");
    
    //Constants (duration in days)
    public static final String basicPlan = "Basic Plan";
    public static final int basicDuration = 1;
    public static final String moderatePlan = "Moderate Plan";
    public static final int moderateDuration = 3;
    public static final String advancedPlan = "Advanced Plan";
    public static final int advancedDuration = 12;

    //Create the file to store the subscription type
    public static final String subscriptionFile = "subscriptions.txt";
    File fileObjectS = new File(subscriptionFile);
    
    
    //Parameterized Constructor (for a new user with no preexisting subscription)
    public Subscriber(String userId, String email, String password, Boolean loggedIn, String subscriptionType, Date subscriptionEnd) {
        super(userId, email, password);
        
        //If we enter a subscriptionType in the constructor, we can automatically subscribe him without calling the method manually
        if(subscriptionType != null){
            subscribe(subscriptionType);
        }
    }

    //Constructor if the user already exists
    public Subscriber(User user) {
        //Gets the user info to keep track
        super(user.getUserId(), user.getEmail(), user.getPassword());
        setLoggedIn(user.isLoggedIn());
        loadSubscriptions();

    }

    //If the user already has a subscription
    public boolean alreadySubscribed() {
        return subscriptionEnd != null && subscriptionEnd.after(new Date());
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionEnd(Date subscriptionEnd) {
        this.subscriptionEnd = subscriptionEnd;
    }

    public Date getSubscriptionEnd() {
        return subscriptionEnd;
    }

    public void subscribe(String whichSubscription) {
        //Stores the type of subscribtion in the instance variable
        this.subscriptionType = whichSubscription;

        //Create a calendar with the current time and date
        Calendar subscriptionCalendar = Calendar.getInstance();
        subscriptionCalendar.setTime(new Date());

        //Check which plan is choosen, and add the duration of that plan to the calendar
        if (subscriptionType.equals(basicPlan)) {
            subscriptionCalendar.add(Calendar.MONTH, basicDuration);
        } else if (subscriptionType.equals(moderatePlan)) {
            subscriptionCalendar.add(Calendar.MONTH, moderateDuration);
        } else if (subscriptionType.equals(advancedPlan)) {
            subscriptionCalendar.add(Calendar.MONTH, advancedDuration);
        }

        //Stores the date after the days are added to the subscriptionEnd instance
        this.subscriptionEnd = subscriptionCalendar.getTime();
        //Saves the date to the file
        saveSubscriptionInFile();
    }

    public void renewSubscription() {

        //Checks if the user does have a subscription already before renewing it
        if (this.subscriptionType != null) {
            //Makes a new calender
            Calendar renewCalendar = Calendar.getInstance();

            if (!alreadySubscribed()) {
                //if the subscribtion already ended,we set the date from now
                renewCalendar.setTime(new Date());
            } else {
                //sets the calendar time to the date the subscription is supposed to end, so we can add from there
                renewCalendar.setTime(this.subscriptionEnd);
            }

            //adds the new date to renew calender, u Cannot change the subscription plan, just add to it
            if (subscriptionType.equals(basicPlan)) {
                renewCalendar.add(Calendar.MONTH, basicDuration);
            } else if (subscriptionType.equals(moderatePlan)) {
                renewCalendar.add(Calendar.MONTH, moderateDuration);
            } else if (subscriptionType.equals(advancedPlan)) {
                renewCalendar.add(Calendar.MONTH, advancedDuration);
            }

            //Override the old subscriptionEnd date
            this.subscriptionEnd = renewCalendar.getTime();
            //Saves the new subscription
            saveSubscriptionInFile();
        }
    }

    private void saveSubscriptionInFile() {
        String line;
        try {
            //Create an arrayList for the subscription
            List<String> subscriptionList = new ArrayList<>();
            //Create a file object for the subscription file to allow you to use methods
            

            if (fileObjectS.exists()) {
                BufferedReader subscriptionBuffferedReader = new BufferedReader(new FileReader(fileObjectS));

                //Read the subscriptions already on the file, if it matches the userId, skip it, we update it and add it later
                while ((line = subscriptionBuffferedReader.readLine()) != null) {
                    String read[] = line.split(",");
                    if (!read[0].equals(getUserId())) {
                        subscriptionList.add(line);
                    }
                }
                subscriptionBuffferedReader.close();

            }
            
            //Create the new subscription with the end date and add it to the arrayList
            String newSubscription = getUserId() + "," + subscriptionType + "," + endDate.format(subscriptionEnd);
            subscriptionList.add(newSubscription);
            
            //Write back all the entries (no true as you are overwriting the file not appending)
            FileWriter subscriptionFileWriter = new FileWriter(subscriptionFile);
            BufferedWriter subscriptioBufferedWriter = new BufferedWriter(subscriptionFileWriter);
            
            //Loops through the list and adds it to the file
            for (String s: subscriptionList) {
                subscriptioBufferedWriter.write(s);
                subscriptioBufferedWriter.newLine();
            }
            subscriptioBufferedWriter.close();
        } catch (IOException e) {
            System.out.println("error when adding to file");
        }

    }
    
    //load all the subscriptions
    private void loadSubscriptions(){
        String line;
        //So the program doesnt fail if the file isnt found
        if(!fileObjectS.exists()){
            return;
        }
        
        try{
            BufferedReader subscriptionBuffferedReader = new BufferedReader(new FileReader(fileObjectS));
            
            //Reads the lines in the fileObject
            while((line = subscriptionBuffferedReader.readLine()) != null) {
                
                //Make a new array and split by commoas
                String[] lbl = line.split(",");
                
                //When the array is 2 indexes long or more (it has the UserId,subscription type, subscription end) and the index at 0 matches the current userId,
                //we set the datafields in the correct index
                if (lbl.length >= 3 && lbl[0].equals(getUserId())) {
                    this.subscriptionType = lbl[1];
                    try{
                        //convert the date from Date to String
                        this.subscriptionEnd = endDate.parse(lbl[2]);
                    }
                    catch(ParseException p){
                        System.out.print("Error converting subscriptionEnd to a string");
                    }
                    break;
                }
            }
            subscriptionBuffferedReader.close();
        }
        catch(IOException ee){
            System.out.print("Error when loading the subscription file");
        }
    }
}
