//import java.util.Comparator;

public class FootballClub extends SportsClub implements Comparable <FootballClub>  {
	
	private int wins, draws, defeats, goalsReceived, goalsScored, matchesPlayed, noOfPoints;
	
	public FootballClub (String name, String location, int playerNumber,
			int wins, int draws, int defeats, int goalsReceived, int goalsScored,
			int matchesPlayed, int noOfPoints) {
		
		
		super(name, location, playerNumber);
		this.wins = wins;
		this.draws=draws;
		this.defeats=defeats;
		this.goalsReceived=goalsReceived;
		this.goalsScored=goalsScored;
		this.matchesPlayed=matchesPlayed;
		this.noOfPoints=noOfPoints;
		
	}
	
	public void displayStatistics()      // this is a method to show the statistics in a table
	{
		System.out.println("number of wins is: " + wins + "\n number of draws is: " + draws + "\n number of defeats is: " + "\n number of goals Received is: "
	+ goalsReceived + "\n number of goals scored is: " + goalsScored + "\n number of matches played is: " + matchesPlayed + "\n number of points is: " +
	noOfPoints);
	}

	public int getWins() {
		return wins;
	}

	public int getDraws() {
		return draws;
	}

	public int getDefeats() {
		return defeats;
	}

	public int getGoalsReceived() {
		return goalsReceived;
	}

	public int getGoalsScored() {
		return goalsScored;
	}

	public int getMatchesPlayed() {
		return matchesPlayed;
	}

	public int getNoOfPoints() {
		return noOfPoints;
	}
	
	public void addGame(int goalsScored, int goalsReceived) {
		matchesPlayed ++;
		this.goalsScored += goalsScored;
		this.goalsReceived += goalsReceived;
		if (goalsScored > goalsReceived) {
			wins++;
			noOfPoints+=3;
		}
		else if (goalsScored < goalsReceived) {
			defeats++;
		}
		else {
			draws++;
			noOfPoints++;
		}
		
	}

	@Override
	public String toString() {
		return getName() + "\t" + getLocation() + "\t:" + getPlayerNumber() + "\t" + wins + "\t:" + draws + "\t:"
				 + defeats + "\t" + goalsReceived + "\t:" + goalsScored + "\t:" + matchesPlayed + "\t:"  +
				noOfPoints + "\n";
	}

	@Override
	public int compareTo (FootballClub o) {
		if (noOfPoints > o.noOfPoints)
			return 1;
		if (noOfPoints < o.noOfPoints)
			return -1;
		int usgd = goalsScored - goalsReceived;
		int ogd = o.goalsScored - o.goalsReceived;
		if (usgd > ogd)
			return 1;
		if (usgd < ogd)
			return -1;
		return 0;
		
	}

	

	
}
