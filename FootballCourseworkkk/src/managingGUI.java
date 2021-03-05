import javax.swing.JFrame;
import java.awt.*;			//
import java.awt.event.ActionEvent;

import javax.swing.*; 		//the x mean java extra libraries, a suffix to the first part of the package name
import java.util.ArrayList;


public class managingGUI extends JFrame {
	private JPanel searchPanel;
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JFrame frame;
	
	PremierLeagueManager myManagerLists;		//I created my own object
	JLabel searchLabel;
	JTextField inputField;
	JButton searchBtn;		//search button
	JTextArea textOutput;		//this is the text being output, all these can be accessed outside a class.
	
	public managingGUI(PremierLeagueManager manager) {
		myManagerLists = manager; //Access to the data

		setTitle("The Premier League");
		setSize(1000, 400);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		//this closes window, allowing it to close once clicking the close button button
		
		frame = new JFrame();
		buttonPanel = new JPanel();
		textPanel = new JPanel();
		searchPanel = new JPanel();
		
		searchLabel = new JLabel("Search match by Date (d/m ie: 10/5 is 10th May): ");
		inputField = new JTextField(15); 			//the size of the search box
		searchBtn = new JButton("Search"); 			//named the buttons 'search'
		
		textOutput = new JTextArea(20, 80);			// rows then columns
		JScrollPane scrollPane = new JScrollPane(textOutput);		//assigned the new value of the textOuptut
		textOutput.setEditable(false);  	//so that text cannot be edited.
		textOutput.setText("Welcome to Football");  		//to input any text into the GUI
		
		JButton displayBtn = new JButton("Teams list");
		JButton sortBtn = new JButton("Sort goals");
		JButton winsBtn = new JButton("Sort wins");
		JButton matchBtn = new JButton("Played match");
		JButton dateBtn = new JButton("Played match by Date");
		
		displayBtn.addActionListener(new ButtonHandler(this, 1)); 		//this refers to us (theApp), action we are associated with this button
		sortBtn.addActionListener(new ButtonHandler(this, 2));
		winsBtn.addActionListener(new ButtonHandler(this, 3));
		matchBtn.addActionListener(new ButtonHandler(this, 4));
		dateBtn.addActionListener(new ButtonHandler(this, 5));
		searchBtn.addActionListener(new ButtonHandler(this, 6));

		searchPanel.add(searchLabel);
		searchPanel.add(inputField); 				//these add the components to the panel, that i have created
		searchPanel.add(searchBtn);
		
		textPanel.add(scrollPane);
		buttonPanel.add(displayBtn);
		buttonPanel.add(sortBtn);
		buttonPanel.add(winsBtn);
		buttonPanel.add(matchBtn);
		buttonPanel.add(dateBtn);

		add(searchPanel, BorderLayout.NORTH);		//this places the search panel to the north of the frame
		add(textPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		

	}

	public static void main(String[] args) {
		new managingGUI(new PremierLeagueManager()).setVisible(true);		//make visible be true

	}
	

	
	
	
	
}




















