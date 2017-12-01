package dashboard.game;

/**
 * Decorator for a Game, it has only knowledge of the game 
 * class.
 * 
 * @author sebastienhamel
 *
 */
public class GameContext {

	/*
	 * Local reference to a Game instance. 
	 * It may be null.
	 */
	private Game game;
	
	public GameContext() {
	
		// empty constructor
	}

	/**
	 * Method that returns the Score string that should be printed
	 * when requested. 
	 * 
	 * @return A String represented Score. 
	 */
	public String getGameScoreString() {
		
		return game.getGameScoreString();
	}
	
	/**
	 * Method used to evaluate if a Game is in progress.
	 * 
	 * @return True if a game is in progress, and false otherwise.
	 */
	public Boolean isGameInProgress() {
		
		if(game == null) {
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Method that clears the current game, nullify it. 
	 */
	public void clear() {
		
		this.game = null;
	}

	/**
	 * Method that adds a point to a team with name. If there is no team with 
	 * the specified name we throw an InvalidTeamException.
	 * 
	 * @param point The point to add.
	 * @param teamName The name of the team we want to add a point to.  
	 * @throws InvalidTeamException
	 */
	public void addPointToTeamWithName(Point point, String teamName) throws InvalidTeamException {
		
		game.addPointToTeamWithName(point, teamName);
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return this.game;
	}
}
