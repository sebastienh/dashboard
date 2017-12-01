package dashboard.game;

import org.checkerframework.checker.igj.qual.ReadOnly;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * 
 * This class represents a team in a game. 
 * 
 * @author sebastienhamel
 *
 */
@ReadOnly
public class Team {

	private final String name;

	private final Score score;
	
	public Team(@NonNull String name) {
		
		this.name = name;
		this.score = new Score();
	}
	
	public void addPoint(Point point) {
		
		score.addPoint(point); 
	}
	
	/**
	 * A method returning true if the current Team has it's name 
	 * instance variable equals to the name parameter.
	 * 
	 * @param name
	 * @return True if the Team is named "name", false otherwise.
	 */
	public Boolean isNamed(String name) {
		
		if(this.name.equals(name)) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Method that compute and return the current Team score representation.
	 * 
	 * @return The team score String value.
	 */
	public String getTeamScore() {
		
		String teamScoreString = this.name + " " + score.count();
		
		if(score.count() > 0) {
			
			teamScoreString += " (" + score + ")";
		}
		
		return teamScoreString;
	}
	
	public boolean equals(Object other) {
		
		if (other == null) {
			return false;
		}
	
		if (other == this) {
			return true;
		}
		  
		if (!(other instanceof Team)){
			return false;
		}
		
		Team otherTeam = (Team)other;
		    
		if(!this.name.equalsIgnoreCase(otherTeam.name)) {
			
			return false;
		}
		
		return true;
	}	
}
