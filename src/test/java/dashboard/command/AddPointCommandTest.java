package dashboard.command;

import org.junit.Assert;
import org.junit.Test;

import dashboard.cli.ConsoleMessage;
import dashboard.cli.Messages;
import dashboard.game.GameContext;

/**
 * Tests for AddPointCommand.
 * 
 * @author sebastienhamel
 *
 */
public class AddPointCommandTest {

	@Test
	public void testAddPointCommandTestWithGameInProgress() {
		
		GameContext context = new GameContext();
		
		StartGameCommand startGameCommand = new StartGameCommand("first team name", "second team name");
	
		startGameCommand.execute(context);
		
		Assert.assertNotNull(context.getGame());
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "second team name", "Haller");
		
		addPointCommand.execute(context);
		
		String actualGameScoreString = context.getGameScoreString();
		
		String expectedGameScoreString = "first team name 0 vs. second team name 1 (Haller 11')";
		
		Assert.assertEquals(expectedGameScoreString, actualGameScoreString);
	}
	
	@Test
	public void testAddPointCommandTestWithoutGameInProgress() {
		
		GameContext context = new GameContext();
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "second team name", "Haller");
		
		ConsoleMessage message = addPointCommand.execute(context);
		
		Assert.assertEquals(Messages.GAME_NOT_STARTED_MESSAGE, message.getMessage());
	}

	@Test
	public void testAddPointCommandTestToUnexistingTeam() {
		
		GameContext context = new GameContext();
		
		StartGameCommand startGameCommand = new StartGameCommand("first team name", "second team name");
		
		startGameCommand.execute(context);
		
		Assert.assertNotNull(context.getGame());
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "not existing team name", "Haller");
		
		ConsoleMessage message = addPointCommand.execute(context);
		
		Assert.assertEquals(Messages.INPUT_ERROR_WHILE_GAME_IN_PROGRESS, message.getMessage());
	}
	
	@Test
	public void testAddPointCommandTestWithPlayerNameWithSpace() {
		
		GameContext context = new GameContext();
		
		StartGameCommand startGameCommand = new StartGameCommand("first team name", "second team name");
		
		startGameCommand.execute(context);
		
		Assert.assertNotNull(context.getGame());
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "first team name", "Haller Test");
		
		addPointCommand.execute(context);
		
		String actualGameScoreString = context.getGameScoreString();
		
		String expectedGameScoreString = "first team name 1 (Haller Test 11') vs. second team name 0";
		
		Assert.assertEquals(expectedGameScoreString, actualGameScoreString);
	}
	
	@Test
	public void testAddPointCommandEqualityFailedWithDifferentValues() {
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "first team name", "Haller Test");
		
		AddPointCommand addPointCommand2 = new AddPointCommand("12", "first team name", "Haller Test");
		
		Assert.assertFalse(addPointCommand.equals(addPointCommand2));
	}
	
	@Test
	public void testAddPointCommandEqualityFailed() {
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "first team name", "Haller Test");
		
		PrintGameScoreCommand endGameCommand2 = new PrintGameScoreCommand();
		
		Assert.assertFalse(addPointCommand.equals(endGameCommand2));
	}
	
	@Test
	public void testAddPointCommandEqualityFailedWithNull() {
		
		AddPointCommand addPointCommand = new AddPointCommand("11", "first team name", "Haller Test");
		
		Assert.assertFalse(addPointCommand.equals(null));
	}
	
}
