public abstract class SportsClub {

	private String name, location;
	private int playerNumber;
	
	public SportsClub (String name, String location, int playerNumber) {
		
		this.name = name;
		this.location = location;
		this.playerNumber = playerNumber;
		
	
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	@Override
	public String toString() {
		return "SportsClub [name=" + name + "]";
	}
	

}
