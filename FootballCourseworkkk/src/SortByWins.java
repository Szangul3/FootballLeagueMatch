import java.util.Comparator;
public class SortByWins implements Comparator<FootballClub> {

	@Override
	public int compare(FootballClub o1, FootballClub o2) {
		
		return o2.getWins() - o1.getWins();		//we return one minus the other
	}
	

}
