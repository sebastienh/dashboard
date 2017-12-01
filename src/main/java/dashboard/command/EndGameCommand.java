package dashboard.command;

import org.checkerframework.checker.igj.qual.ReadOnly;
import org.checkerframework.checker.nullness.qual.NonNull;

import dashboard.cli.ConsoleMessage;
import dashboard.cli.Messages;
import dashboard.game.GameContext;

/**
 * The EndGameCommand ends a Game, print the Game score 
 * and clear the GameContext object of it's game. Making it
 * ready to start a new one. 
 * 
 * @author sebastienhamel
 *
 */
@ReadOnly
public class EndGameCommand extends AbstractCommand {

	public EndGameCommand() {
		
		// empty constructor
	}

	@Override
	public @NonNull ConsoleMessage execute(GameContext context) {
		
		if(!context.isGameInProgress()) {
			
			return new ConsoleMessage(Messages.GAME_NOT_STARTED_MESSAGE);
		}
		
		ConsoleMessage consoleMessage = new ConsoleMessage(context.getGameScoreString());
		
		context.clear();
		
		return consoleMessage;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if (other == null) {
			return false;
		}
	
		if (other == this) {
			return true;
		}
		  
		if (!(other instanceof EndGameCommand)){
			return false;
		}
		
		return true;
	}	
	
}
