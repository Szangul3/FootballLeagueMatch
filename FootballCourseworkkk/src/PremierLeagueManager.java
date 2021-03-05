import java.io.*;
import java.util.ArrayList;            //imports the array list from
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.util.Arrays.asList;
//import java.io.FileWriter;
//import java.io.IOException;


public class PremierLeagueManager implements LeagueManager {  //by implementing class LeagueManager, the class should provide the implementation details of the methods within the interface.

    private List<FootballClub> clubs;
    private List<Match> matches;
    private Scanner sc;
    private managingGUI gui; // The gui.

    public PremierLeagueManager() {
        clubs = new ArrayList<>();
        matches = new ArrayList<>();
        sc = new Scanner(System.in);    //i put the scanner here so that all the methods do not need a new one each time
        checkFilesExist();
        gui = new managingGUI(this); // send this instance to the gui
    }

    public static void main(String[] args) {
        PremierLeagueManager manager = new PremierLeagueManager();      //creating a new premier league manager object
        while (true) {
            System.out.println(manager);
            manager.displayMenu();
            manager.getOptions();
        }
    }


    public String allClubsWithStats() {
        String result = "Team\tCity\tPlr\tWin\tDrw\tDfts\tGlsR\tGlsS\tPlyd\tPoints\n";
        for (FootballClub club : clubs) {        //this 'for' loop will make it go through each of them (statistics).
            result = result + club.toString();        //this returns the club statistics as a string and add it to the buttons
        }

        return result;
    }


    public String allMatches(){
        String result = "Date\tTeam\tS:S\tTeam\n\n";
        for (Match match: matches){
            result += match.toString() + "\n";
        }
        return result;
    }
    public void setClubs() { // Dummy set of data for testing - also used if teamData.txt doesn't exist.
        final List<String> names = asList("Tottenham", "Chelsea", "Fulham", "Liverpool", "Barcelona", "Arsenal");
        final List<String> locations = asList("London", "London", "London", "Liverpool", "Spain", "London");
        for (int i = 0; i < names.size(); i++) {
            FootballClub tempClub = new FootballClub(names.get(i), locations.get(i), i + 1, 0, 0, 0, 0, 0, 0, 0);
            clubs.add(tempClub);
        }

        saveClubs();                        //if this breaks, it will throw an exception and the exception is called, therefore it does not crash.

    }

    public void displayMenu() {
        System.out.println("1 creates a new football clubs\n"
                + "2 deletes an existing football club \n"
                + "3 displays the various statistics for selected club\n"
                + "4 displays the Premier League Table\n"
                + "5 adds the played game\n"
                + "6 show GUI");

    }


    public void getOptions() {
        String option;
        option = sc.nextLine();
        FootballClub club;                //declaring a variable so that I can use it to input a new football club


        switch (option) {
            case "1":
                System.out.println("1 create a football club");
                club = createFootballClub();        //this calls the method to create a football Club
                clubs.add(club);            //adds the clubs to the premier league list.

                saveClubs();

                break;
            case "2":

                club = getFootballClub();
                clubs.remove(club);

                break;
            case "3":
                club = getFootballClub();
                club.displayStatistics();
                break;
            case "4":
                System.out.println("4 selected, the Premier League Table");
                //adds the clubs to the premier league list.
                sortClubs();    //
                for (FootballClub c : clubs) {
                    System.out.println(c.getName());
                    c.displayStatistics();        //display the statistics
                }
                break;
            case "5":
                System.out.println("Please input the names of the team, with their scores and match date");
                playedMatch();
                break;
            case "6":
                System.out.print("Running GUI now!");
                gui.setVisible(true);
            default:
                System.out.println("Please select a valid option from the list");
        }
        ;
    }

    //Sorting methods.
    public void sortMatchesAsc(){ Collections.sort(matches); }
    public void sortClubs() {
        Collections.sort(clubs, Collections.reverseOrder());
    }
    public void sortByGoals() { Collections.sort(clubs, new SortByGoal()); }       //sorting by using our sort by goals object
    public void sortByWins() {
        Collections.sort(clubs, new SortByWins());
    }

    public FootballClub createFootballClub() {                //all of these information are asked from the user to input in order to create
        System.out.println("Please enter name");                //a football club
        String name = sc.nextLine();
        System.out.println("Please enter location");
        String location = sc.nextLine();
        System.out.println("Please enter player Number");
        int playerNumber = sc.nextInt();
        System.out.println("Please enter number of wins");
        int wins = sc.nextInt();
        System.out.println("Please enter number of draws");
        int draws = sc.nextInt();
        System.out.println("Please enter defeats");
        int defeats = sc.nextInt();
        System.out.println("Please enter number of goals received");
        int goalsReceived = sc.nextInt();
        System.out.println("Please enter the goals scored");
        int goalsScored = sc.nextInt();
        System.out.println("Please enter matches played");
        int matchesPlayed = sc.nextInt();
        System.out.println("Please enter noOfPoints");
        int noOfPoints = sc.nextInt();
        FootballClub club = new FootballClub(name, location, playerNumber, wins, draws, defeats, goalsReceived, goalsScored, matchesPlayed, noOfPoints);
        return club;     //this returns the values that are input into the variable club

    }

    public FootballClub getFootballClub() {

        System.out.println("Enter a club name");                //adds the clubs to the premier league list.
        String footballClub = sc.nextLine();                    //gets an input from the user
        return searchClub(footballClub);
    }

    public FootballClub searchClub(String term) {
        for (FootballClub club : clubs) {
            if (club.getName().contentEquals(term)) {
                return club;
            }
        }
        return null;//if no value is input and no values to return, it will return null as default
    }




    public int getSize() {
        return clubs.size();            //this is to keep clubs size private
    }

    public List<Match> getMatches() {
        return matches;
    }

    public FootballClub getRandomClub() {
        Random r = new Random();

        return clubs.get(r.nextInt(getSize()));

    }

    public void playedMatch() {
        FootballClub club1 = getFootballClub();
        FootballClub club2 = getFootballClub();
        System.out.println("Please input the date of the game played. Format: d/m (ie: 10/3 for 10th of March)");
        String datePlayed = sc.nextLine();
        System.out.println("Please input the score (number of goals) of the first team");
        int team1score = sc.nextInt();
        System.out.println("Please input the score (number of goals) of the second team");
        int team2score = sc.nextInt();

        addMatch(datePlayed,club1,club2,team1score,team2score);

    }

    public void addMatch(String date, FootballClub club1, FootballClub club2, int team1Goals, int team2Goals){
        Match tempMatch = new Match(date, club1.getName(), club2.getName(),team1Goals, team2Goals );
        matches.add(tempMatch);
        club1.addGame(team1Goals, team2Goals);   ///this method updates the scores in the league table once its called.
        club2.addGame(team2Goals, team1Goals);
        saveMatches();
        saveClubs();
    }



    private void checkFilesExist(){

        File f = new File("teamData.txt");

        // Check if the specified file
        // Exists or not
        if (f.exists()){
            loadClubs();
        } else {
            setClubs(); // add dummy data
        }

        f = new File("matchData.txt");

        if (f.exists()){
            loadMatches();
        } else {
            addMatch("10/5",clubs.get(0),clubs.get(1),0,0); //dummy match to add
        }


    }


    private void saveFile(String dataString, String filename) throws FileNotFoundException {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(dataString);
            writer.close();
        } catch (IOException e) {
            System.out.println("Problem saving the file");

        }
    }

    private String loadFile(String filename) throws FileNotFoundException {
        String message = "";
        try {
            FileReader reader = new FileReader(filename);
            int character;

            while ((character = reader.read()) != -1) {
                message += (char) character;
            }
            reader.close();
        } catch(IOException e){
            System.out.println("Problem loading the file");
        }
        return message;
    }

    public void saveClubs() {
        String toWrite = "";
        for (FootballClub club : clubs) {
            toWrite += club.getName() +
                    "," + club.getLocation() +
                    "," + club.getPlayerNumber() +
                    "," + club.getWins() +
                    "," + club.getDraws() +
                    "," + club.getDefeats() +
                    "," + club.getGoalsReceived() +
                    "," + club.getGoalsScored() +
                    "," + club.getMatchesPlayed() +
                    "," + club.getNoOfPoints() +
                    "\n";
        }
        try {
            saveFile(toWrite,"teamData.txt");

        } catch (FileNotFoundException e) {
            System.out.println("Problem saving the team data");
        }

    }

    public void saveMatches(){
        String toWrite = "";
        for (Match match : matches){
            toWrite += match.getDate() +
                    "," + match.getTeams()[0] +
                    "," + match.getTeams()[1] +
                    "," + match.getTeam1Score() +
                    "," + match.getTeam2Score() +
                    "\n";
        }
        try {
            saveFile(toWrite, "matchData.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Problem saving the matches data");
        }
    }

    private void loadClubs() {
        try {
            String message = loadFile("teamData.txt");
            String[] lines = message.split("\n");
            for (String line : lines) {

                String[] stats = line.split(",");
                String name = stats[0];
                String location = stats[1];
                int playerNumber = Integer.parseInt(stats[2]);// may need validation code first. But ok here.
                int wins = Integer.parseInt(stats[3]);
                int draws = Integer.parseInt(stats[4]);
                int defeats = Integer.parseInt(stats[5]);
                int goalsReceived = Integer.parseInt(stats[6]);
                int goalsScored = Integer.parseInt(stats[7]);
                int matchesPlayed = Integer.parseInt(stats[8]);
                int noOfPoints = Integer.parseInt(stats[9]);


                FootballClub tempTeam = new FootballClub(name, location, playerNumber, wins,
                        draws, defeats, goalsReceived, goalsScored, matchesPlayed, noOfPoints);

                clubs.add(tempTeam);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadMatches(){
        try{
            String message = loadFile("matchData.txt");
            String[] lines = message.split("\n");
            for (String line : lines) {
                String[] stats = line.split(",");
                String date = stats[0];
                String club1 = stats[1];
                String club2 = stats[2];
                int team1Goals = Integer.parseInt(stats[3]);
                int team2Goals = Integer.parseInt(stats[4]);

                Match tempMatch = new Match(date, club1, club2,team1Goals, team2Goals );
                matches.add(tempMatch);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }



    @Override
    public String toString() {
        return allClubsWithStats();
    }
}

	 

