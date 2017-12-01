package dashboard.command;

import org.checkerframework.checker.igj.qual.ReadOnly;

import dashboard.cli.ConsoleMessage;
import dashboard.cli.Messages;
import dashboard.game.GameContext;

/**
 * Class that represents a command to print the game score.
 * 
 * @author sebastienhamel
 *
 */
@ReadOnly
public class PrintGameScoreCommand extends AbstractCommand {

	public PrintGameScoreCommand() {
		
		// empty constructor
	}

	/*
	 * (non-Javadoc)
	 * @see dashboard.command.Command#execute(dashboard.game.GameContext)
	 */
	@Override
	public ConsoleMessage execute(GameContext context) {

		if(!context.isGameInProgress()) {
			
			return new ConsoleMessage(Messages.GAME_NOT_STARTED_MESSAGE);
		}
		
		return new ConsoleMessage(context.getGameScoreString());
	}
	
	@Override
	public boolean equals(Object other) {
		
		if (other == null) {
			return false;
		}
	
		if (other == this) {
			return true;
		}
		  
		if (!(other instanceof PrintGameScoreCommand)){
			return false;
		}
		
		return true;
	}	
}
