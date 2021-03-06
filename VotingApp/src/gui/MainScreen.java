/**
 * 
 */
package gui;

import Driver.RegisteredUser;
import Driver.GeneralUser;
import Driver.VotingThread;
import database.ThreadDataBase;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Ivan Montoya
 *
 */
public class MainScreen extends JFrame{
	/**Label that displays the user name.*/
	private JLabel myUsername;
	/**Holds Create Thread button.*/
	private JButton myCreateButton;
	/**Holds View Threads button.*/
	private JButton myViewButton;
	/**Holds Log out Button.*/
	private JButton myLogOutButton;
	/**Holds dank MEME.*/
	private ImageIcon myImage;
	
	private List<VotingThread> myThreads;
	private ThreadDataBase myDatabase;
	private RegisteredUser myUser;
	
	public MainScreen(RegisteredUser user) {
		
		myUser = user;
		myDatabase = new ThreadDataBase();
		myUsername = new JLabel("User: " + user.getUser());
		myImage = new ImageIcon("Images/thread.jpg");
		setCreateButton();
		setViewButton();
		setLogOutButton();
		start();
	}
	
	/**
	 * Method used to set up components inside the JFrame.
	 */
	public void start() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel topPanel = new JPanel();
		
		// Adds components to the JFrame
		GridLayout grid = new GridLayout(2, 2, 120, 0);
		//this.setLayout(grid);
		topPanel.add(viewProfileButton());
		topPanel.add(myUsername);
		topPanel.add(myLogOutButton);
		
		JLabel centerLabel = new JLabel(myImage);
		
		JPanel bottomPanel = new JPanel();
		
		bottomPanel.add(myCreateButton);
		bottomPanel.add(myViewButton);
		bottomPanel.add(new JButton("View Private Groups"));
		bottomPanel.add(new JButton("View My Threads"));
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerLabel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		this.setTitle("Main");
		
		// Sets dimensions and location
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.setResizable(false);
        this.setVisible(true);
	}
	
	private JButton viewProfileButton() {
		JButton viewProfileButton = new JButton("View Profile");
		viewProfileButton.addActionListener(viewProfileAction());
		
		return viewProfileButton;
	}
	
	private ActionListener viewProfileAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewProfileScreen(myUser);
			}
		};
	}
	
	/**
	 * Method used to set the Create New Thread button. Button is used to open
	 * up the Create Thread Screen.
	 */
	private void setCreateButton() {
		myCreateButton = new JButton("Create New Thread");
		
		myCreateButton.addActionListener( new ActionListener() {
			
			/**
			 * Opens Create Thread Screen
			 */
			public void actionPerformed(ActionEvent e) {
				new CreateThreadScreen(myDatabase);
			}
		});
	}
	
	/**
	 * Method used to set the View Threads button. Button is used to open
	 * the View Threads Screen.
	 */
	private void setViewButton() {
		myViewButton = new JButton("View Threads");
		
		myViewButton.addActionListener( new ActionListener() {
			
			/**
			 * Opens View Thread Screen
			 */
			public void actionPerformed(ActionEvent e) {
				new ViewThreadsScreen(myDatabase);
			}
		});
	}
	
	/**
	 * Sets up Log Out Button. Button is used to simply log out.
	 */
	private void setLogOutButton() {
		myLogOutButton = new JButton("Log out");
		
		myLogOutButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new WelcomeScreen();
				dispose();
			}
		});
	}
}
