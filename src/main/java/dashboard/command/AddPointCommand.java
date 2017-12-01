package dashboard.command;

import org.checkerframework.checker.igj.qual.ReadOnly;

import dashboard.cli.ConsoleMessage;
import dashboard.cli.Messages;
import dashboard.game.GameContext;
import dashboard.game.InvalidTeamException;
import dashboard.game.Minute;
import dashboard.game.Point;

/**
 * AddPointCommand represents the results of parsing the user inputs to 
 * add a point.  
 * 
 * "Acceptable inputs to tell the Dashboard when goals are scored follow the following 
 * structure: "<minute> '<Team>' <name of scorer>"."
 *
 * a. Example: "11 'West Germany' Haller"
 * 
 * @author sebastienhamel
 *
 */
@ReadOnly
public class AddPointCommand extends AbstractCommand {

	private final String minuteString;
	
	private final String teamName;
	
	private final String player;
	
	public AddPointCommand(String minuteString, String teamName, String player) {
		
		this.minuteString = minuteString;
		this.teamName = teamName;
		this.player = player;
	}

	@Override
	public ConsoleMessage execute(GameContext context) {

		if(!context.isGameInProgress()) {
			
			return new ConsoleMessage(Messages.GAME_NOT_STARTED_MESSAGE);
		}
		
		Minute minute = Minute.CreateFromString(minuteString);
		
		Point point = new Point(player, minute);
		
		try {
		
			context.addPointToTeamWithName(point, teamName);
			
			return new ConsoleMessage();
		}
		catch(InvalidTeamException ite) {
			
			return new ConsoleMessage(Messages.INPUT_ERROR_WHILE_GAME_IN_PROGRESS);
		}
	}
	
	@Override
	public boolean equals(Object other) {
		
		if (other == null) {
			return false;
		}
	
		if (other == this) {
			return true;
		}
		  
		if (!(other instanceof AddPointCommand)){
			return false;
		}
		
		AddPointCommand otherAddPointCommand = (AddPointCommand)other;
		    
		if(!this.teamName.equalsIgnoreCase(otherAddPointCommand.getTeamName())) {
			
			return false;
		}
		
		if(!this.player.equalsIgnoreCase(otherAddPointCommand.getPlayer())) {
			
			return false;
		}
		
		if(!this.minuteString.equalsIgnoreCase(otherAddPointCommand.getMinuteString())) {
			
			return false;
		}
		
		return true;
	}	
	
	public String getMinuteString() {
		return minuteString;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getPlayer() {
		return player;
	}
}
