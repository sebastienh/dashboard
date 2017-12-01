package dashboard.command;

import org.junit.Assert;
import org.junit.Test;

import dashboard.cli.ConsoleMessage;
import dashboard.cli.Messages;
import dashboard.game.GameContext;

/**
 * Tests for PrintGameScoreCommand.
 * 
 * @author sebastienhamel
 *
 */
public class PrintGameScoreCommandTest {

	
	@Test
	public void testPrintGameScoreCommandTestWithGameInProgress() {
		
		GameContext context = new GameContext();
		
		StartGameCommand startGameCommand = new StartGameCommand("first team name", "second team name");
	
		startGameCommand.execute(context);
		
		Assert.assertNotNull(context.getGame());
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "second team name", "Haller");
		
		addPointCommand.execute(context);
		
		PrintGameScoreCommand printGameScoreCommand = new PrintGameScoreCommand();
		
		ConsoleMessage consoleMessage = printGameScoreCommand.execute(context);
		
		String expectedGameScoreString = "first team name 0 vs. second team name 1 (Haller 11')";
		
		Assert.assertEquals(expectedGameScoreString, consoleMessage.getMessage());
	}
	
	@Test
	public void testPrintGameScoreCommandTestMultiplePointsWithGameInProgress() {
		
		GameContext context = new GameContext();
		
		StartGameCommand startGameCommand = new StartGameCommand("first team name", "second team name");
	
		startGameCommand.execute(context);
		
		Assert.assertNotNull(context.getGame());
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "second team name", "Haller");
		
		addPointCommand.execute(context);
		
		addPointCommand = new AddPointCommand("13", "second team name", "Haller");
		
		addPointCommand.execute(context);
		
		PrintGameScoreCommand printGameScoreCommand = new PrintGameScoreCommand();
		
		ConsoleMessage consoleMessage = printGameScoreCommand.execute(context);
		
		String expectedGameScoreString = "first team name 0 vs. second team name 2 (Haller 11' Haller 13')";
		
		Assert.assertEquals(expectedGameScoreString, consoleMessage.getMessage());
	}
	
	@Test
	public void testPrintGameScoreCommandTestWithoutGameInProgress() {
		
		GameContext context = new GameContext();
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "second team name", "Haller");
		
		addPointCommand.execute(context);
		
		PrintGameScoreCommand printGameScoreCommand = new PrintGameScoreCommand();
		
		ConsoleMessage consoleMessage = printGameScoreCommand.execute(context);
		
		Assert.assertEquals(Messages.GAME_NOT_STARTED_MESSAGE, consoleMessage.getMessage());
	}
	
	@Test
	public void testPrintGameScoreCommandEqualityFailedWithNull() {
		
		PrintGameScoreCommand printGameScoreCommand = new PrintGameScoreCommand();
		
		Assert.assertFalse(printGameScoreCommand.equals(null));
	}
	
	@Test
	public void testPrintGameScoreCommandEqualityFailed() {
		
		PrintGameScoreCommand printGameScoreCommand = new PrintGameScoreCommand();
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "second team name", "Haller");
		
		Assert.assertFalse(printGameScoreCommand.equals(addPointCommand));
	}
	
}
