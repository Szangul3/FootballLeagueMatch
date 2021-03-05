import java.util.Comparator;
public class SortByGoal implements Comparator<FootballClub> {

	@Override
	public int compare(FootballClub o1, FootballClub o2) {
		
		return o2.getGoalsScored() - o1.getGoalsScored();		//we return one minus the other
	}
	

}
