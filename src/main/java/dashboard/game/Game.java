package dashboard.game;

import org.checkerframework.checker.igj.qual.ReadOnly;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * The class Game represent a game instance. It is created when the 
 * "Start:" command is issued by the user.
 * 
 * @author sebastienhamel
 *
 */
@ReadOnly
public class Game {

	private Team homeTeam;
	
	private Team awayTeam;
	
	public Game(@NonNull Team homeTeam, @NonNull Team awayTeam) {
		
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	/**
	 * Method that returns the game score, meaning the score for both
	 * teams participating in the game, the home team and the away team.
	 * 
	 * @return
	 */
	public String getGameScoreString() {
		
		return homeTeam.getTeamScore() + " vs. " + awayTeam.getTeamScore();
	}
	
	/**
	 * Method that adds a point to the team named "teamName".
	 * 
	 * @param point
	 * @param teamName
	 * @throws InvalidTeamException
	 */
	public void addPointToTeamWithName(Point point, String teamName) throws InvalidTeamException {
		
		Team team = getTeamNamed(teamName);
		
		team.addPoint(point);
	}
	
	/**
	 * This method use the teamName parameter to find the Team 
	 * with the same name.
	 * 
	 * @param teamName
	 * @return
	 * @throws InvalidTeamException
	 */
	private Team getTeamNamed(String teamName) throws InvalidTeamException {
		
		if(homeTeam.isNamed(teamName)) {
			
			return homeTeam;
		}
		else if(awayTeam.isNamed(teamName)) {
			
			return awayTeam;
		}
		
		throw new InvalidTeamException();
	}
	
	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}
}
