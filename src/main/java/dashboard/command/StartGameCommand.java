package dashboard.command;

import dashboard.cli.ConsoleMessage;
import dashboard.cli.Messages;
import dashboard.game.Game;
import dashboard.game.GameContext;
import dashboard.game.Team;

/**
 * 
 * Class that represent a command to start a game.
 * 
 * @author sebastienhamel
 *
 */
public class StartGameCommand extends AbstractCommand {

	private final String homeTeamName;
	
	private final String awayTeamName;
	
	public StartGameCommand(String homeTeamName, String awayTeamName) {
		
		this.homeTeamName = homeTeamName;
		this.awayTeamName = awayTeamName;
	}

	@Override
	public ConsoleMessage execute(GameContext context) {
		
		if (context.isGameInProgress()) {
			
			// we are trying to create a second game while there is already 
			// one created.
			return new ConsoleMessage(Messages.GAME_ALREADY_STARTED_MESSAGE);
		}
		else {
			
			Team homeTeam = new Team(homeTeamName);
			Team awayTeam = new Team(awayTeamName);
			
			Game game = new Game(homeTeam, awayTeam);
			context.setGame(game);
		}
		
		return new ConsoleMessage();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if (other == null) {
			return false;
		}
	
		if (other == this) {
			return true;
		}
		  
		if (!(other instanceof StartGameCommand)){
			return false;
		}
		
		StartGameCommand otherStartGameCommand = (StartGameCommand)other;
		    
		if(!this.homeTeamName.equalsIgnoreCase(otherStartGameCommand.getHomeTeamName())) {
			
			return false;
		}
		
		if(!this.awayTeamName.equalsIgnoreCase(otherStartGameCommand.getAwayTeamName())) {
			
			return false;
		}
		
		return true;
	}	
	
	public String getHomeTeamName() {
		return homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}
}
