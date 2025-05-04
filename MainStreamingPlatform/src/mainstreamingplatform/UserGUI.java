package mainstreamingplatform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserGUI {

    //GUI components
    private JFrame frame; //frame
    
    private JLabel idLabel, emailLabel, passwordLabel;
    
    private JTextField idField; //userid input field
    private JTextField emailField; //email inpout field
    private JTextField passwordField; //password input field

    private JButton loginButton;
    

    private User user1;//Link to the User Object

    //No-Arg constructor
    public UserGUI() {

        //Setting up the frame box
        frame = new JFrame("Login Page");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Setting up the panel
        JPanel panel = new JPanel(new GridLayout()); //Create the panel
        panel.setLayout(null); //Set the panel to null to allow freedom of setting your own bounds

        //Allow the user to input their userId, email, and password
        idLabel = new JLabel("User ID: ");
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        idLabel.setBounds(100, 100, 80, 25);
        panel.add(idLabel);
        
        
        idField = new JTextField();
        idField.setBounds(200, 100, 150, 25);
        panel.add(idField);

        emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailLabel.setBounds(100, 150, 80, 25);
        panel.add(emailLabel);
        
        
        emailField = new JTextField();
        emailField.setBounds(200, 150, 150, 25);
        panel.add(emailField);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setBounds(100, 200, 80, 25);
        panel.add(passwordLabel);
        
        
        passwordField = new JTextField();
        passwordField.setBounds(200, 200, 150, 25);
        panel.add(passwordField);
        
        loginButton = new JButton("Login: "); //Login button
        loginButton.setBounds(200, 250, 150, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent login) {

                String UserId = idField.getText();
                String Email = emailField.getText();
                String Password = passwordField.getText();

                User u = new User(UserId, Email, Password);
                
                JOptionPane.showMessageDialog(frame,"Login sucessful, Welcome " + UserId);
            }
            
        });
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
    public User getUser(){
        return user1;
    }
}
