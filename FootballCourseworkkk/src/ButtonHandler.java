import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ButtonHandler implements ActionListener {
	private managingGUI theApp;
	private int action;
	
	//JColorChooser chooser;
	
	
	public ButtonHandler(managingGUI theApp, int action) {
		this.theApp = theApp; 		//
		this.action = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (action == 1)  { // List clubs
			theApp.myManagerLists.sortClubs();
			theApp.textOutput.setText(theApp.myManagerLists.allClubsWithStats());  		//this returns the strings of the stats
		}
		
		
		if (action == 2)  {
				//theApp.textOutput.setText("Goal sorting Button");
				theApp.myManagerLists.sortByGoals();
				theApp.textOutput.setText(theApp.myManagerLists.allClubsWithStats());  		//this returns the strings of the stats
		}
		
		if (action == 3)  {
			theApp.textOutput.setText("Wins sorting Button");
			theApp.myManagerLists.sortByWins();
			theApp.textOutput.setText(theApp.myManagerLists.allClubsWithStats()); 
		}
		
		if (action == 4)  {
			FootballClub club1 = theApp.myManagerLists.getRandomClub();
			FootballClub club2 = theApp.myManagerLists.getRandomClub();
			while (club2.equals(club1)) {
				club2 = theApp.myManagerLists.getRandomClub();
				}
			
			Random r = new Random();
			int goals1 = r.nextInt(10);
			int goals2 = r.nextInt(10); 		//set the 'int' limit to 10

			int day = r.nextInt(30) + 1; // 0 to 30 + 1 to get range 1 - 31
			int month = r.nextInt(11) + 1;
			String date = day + "/" + month;

			theApp.myManagerLists.addMatch(date,club1,club2,goals1,goals2);

			theApp.textOutput.setText("Match played\n-----------\n");
			theApp.textOutput.append(club1.getName()+ " " + goals1 + ":" + goals2 + " " + club2.getName() + "\n\n");
			theApp.textOutput.append(theApp.myManagerLists.allClubsWithStats());  		//this returns the strings of the stats
			
		}
		
		if (action == 5)  {

			theApp.myManagerLists.sortMatchesAsc();
			theApp.textOutput.setText(theApp.myManagerLists.allMatches());


		}
		
		if (action == 6)  {
			boolean found = false;
			String inputDate = theApp.inputField.getText();			//whatever is put into the text field, it is being extracted and put into the text area.

			theApp.inputField.setText(""); //Reset the search field to make it look like the information was taken in.

			List<Match> matches = theApp.myManagerLists.getMatches();
			List<Match> result = new ArrayList<>();
			for (Match match : matches){
				if (match.getDate().equals(inputDate)){
					result.add(match);
					found = true;
				}
			}


			if (found) {
				theApp.textOutput.setText("Matches played on: " + inputDate + "\n");
				theApp.textOutput.append("Date\tTeam\tS:S\tTeam\n\n");
				for (Match match : result) {
					theApp.textOutput.append(match.toString() + "\n");
				}
			} else {
				theApp.textOutput.setText("Sorry, no matches found for that date");
			}
			
		}
			
			
		theApp.repaint();  		//refreshes display because something has changed.
	}

}
