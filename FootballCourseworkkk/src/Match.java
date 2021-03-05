import java.util.Arrays;

public class Match implements Comparable <Match>  {
    private String date;
    private String[] teams;
    private String score;
    private int team1Score;
    private int team2Score;

    public Match(String date, String team1, String team2, int team1Score, int team2Score){
        this.date = date;
        this.score = team1Score + ":" + team2Score;
        this.teams = new String[]{team1, team2};
        this.team1Score = team1Score;
        this.team2Score = team2Score;

    }
    public String getDate(){return date;}
    public int getTeam1Score(){return team1Score;}
    public int getTeam2Score(){return team2Score;}
    public String[] getTeams(){return teams;}



    @Override
    public String toString() {
        return date + "\t" + teams[0] + "\t" + score + "\t" + teams[1];
    }

    @Override
    public int compareTo(Match other) {
        String[] thisDate = date.split("/");
        String[] otherDate = other.getDate().split("/");
        int thisDay = Integer.parseInt(thisDate[0]);
        int otherDay = Integer.parseInt(otherDate[0]);
        int thisMonth = Integer.parseInt(thisDate[1]);
        int otherMonth = Integer.parseInt(otherDate[1]);

        if (thisMonth == otherMonth) {
            if (thisDay > otherDay){
                return 1;
            }
            if (thisDay < otherDay){
                return -1;
            }
        }

        if (thisMonth > otherMonth) {
            return 1;
        }
        if (thisMonth < otherMonth) {
            return -1;
        }



        return 0;
    }
}
