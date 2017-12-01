package dashboard.command;

import org.junit.Assert;
import org.junit.Test;

import dashboard.cli.ConsoleMessage;
import dashboard.cli.Messages;
import dashboard.game.GameContext;

/**
 * Tests for EndGameCommand.
 * 
 * @author sebastienhamel
 *
 */
public class EndGameCommandTest {

	@Test
	public void testEndGameCommandWithGameInProgress() {
		
		GameContext context = new GameContext();
		
		StartGameCommand startGameCommand = new StartGameCommand("first team name", "second team name");
	
		startGameCommand.execute(context);
		
		Assert.assertNotNull(context.getGame());
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "second team name", "Haller");
		
		addPointCommand.execute(context);
		
		EndGameCommand endGameCommand = new EndGameCommand();
		
		ConsoleMessage consoleMessage = endGameCommand.execute(context);
		
		String expectedGameScoreString = "first team name 0 vs. second team name 1 (Haller 11')";
		
		Assert.assertEquals(expectedGameScoreString, consoleMessage.getMessage());
	}
	
	@Test
	public void testEndGameCommandClearedContextWithGameInProgress() {
		
		GameContext context = new GameContext();
		
		StartGameCommand startGameCommand = new StartGameCommand("first team name", "second team name");
	
		startGameCommand.execute(context);
		
		Assert.assertNotNull(context.getGame());
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "second team name", "Haller");
		
		addPointCommand.execute(context);
		
		EndGameCommand endGameCommand = new EndGameCommand();
		
		endGameCommand.execute(context);

		Assert.assertNull(context.getGame());
	}
	
	@Test
	public void testEndGameCommandWithoutGameInProgress() {
		
		GameContext context = new GameContext();
		
		EndGameCommand endGameCommand = new EndGameCommand();
		
		ConsoleMessage consoleMessage = endGameCommand.execute(context);
		
		Assert.assertEquals(Messages.GAME_NOT_STARTED_MESSAGE, consoleMessage.getMessage());
	}
	
	@Test
	public void testEndGameCommandEquality() {
		
		EndGameCommand endGameCommand1 = new EndGameCommand();
		
		EndGameCommand endGameCommand2 = new EndGameCommand();
		
		Assert.assertTrue(endGameCommand1.equals(endGameCommand2));
	}

	@Test
	public void testEndGameCommandEqualityFailed() {
		
		EndGameCommand endGameCommand1 = new EndGameCommand();
		
		PrintGameScoreCommand endGameCommand2 = new PrintGameScoreCommand();
		
		Assert.assertFalse(endGameCommand1.equals(endGameCommand2));
	}
	
	@Test
	public void testEndGameCommandEqualityFailedWithNull() {
		
		EndGameCommand endGameCommand1 = new EndGameCommand();
		
		Assert.assertFalse(endGameCommand1.equals(null));
	}
	
}

