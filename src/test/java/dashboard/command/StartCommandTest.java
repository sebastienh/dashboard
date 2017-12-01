package dashboard.command;

import org.junit.Assert;
import org.junit.Test;

import dashboard.cli.ConsoleMessage;
import dashboard.cli.Messages;
import dashboard.game.GameContext;

/**
 * Tests for StartCommand.
 * 
 * @author sebastienhamel
 *
 */
public class StartCommandTest {

	@Test
	public void testBasicStartCommandWithNoGameInProgress() {
		
		GameContext context = new GameContext();
		
		StartGameCommand startGameCommand = new StartGameCommand("first team name", "second team name");
	
		startGameCommand.execute(context);
		
		Assert.assertNotNull(context.getGame());
	}
	
	@Test
	public void testBasicStartCommandWithGameInProgress() {
		
		GameContext context = new GameContext();
		
		StartGameCommand startGameCommand = new StartGameCommand("first team name", "second team name");
	
		startGameCommand.execute(context);
		
		Assert.assertNotNull(context.getGame());
		

		// execute a second time the same command
		ConsoleMessage gameAlreadyStartedErrorMessage = startGameCommand.execute(context);
		
		Assert.assertEquals(gameAlreadyStartedErrorMessage.getMessage(), Messages.GAME_ALREADY_STARTED_MESSAGE);
	}
	
	@Test
	public void testAddPointCommandEqualityFailedWithNull() {
		
		StartGameCommand startGameCommand = new StartGameCommand("first team name", "second team name");
		
		Assert.assertFalse(startGameCommand.equals(null));
	}
	
	@Test
	public void testAddPointCommandEqualityFailed() {
		
		StartGameCommand startGameCommand = new StartGameCommand("first team name", "second team name");
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "first team name", "Haller Test");
		
		Assert.assertFalse(startGameCommand.equals(addPointCommand));
	}
	
	@Test
	public void testAddPointCommandEqualityFailedDifferentValues() {
		
		StartGameCommand startGameCommand1 = new StartGameCommand("first team name", "second team name");
		
		StartGameCommand startGameCommand2 = new StartGameCommand("first team name1", "second team name");
		
		Assert.assertFalse(startGameCommand1.equals(startGameCommand2));
	}
	
}
