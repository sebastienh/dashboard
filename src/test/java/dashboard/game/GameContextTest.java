package dashboard.game;

import org.junit.Assert;
import org.junit.Test;


/**
 * Test for GameContext.
 * 
 * @author sebastienhamel
 *
 */
public class GameContextTest {

	@Test 
	public void testCreateGameContextNoGame() {
	
		GameContext context = new GameContext();
		
		Assert.assertNull(context.getGame());
	}
	
	@Test 
	public void testCreateGameContextNoGameInProgress() {
	
		GameContext context = new GameContext();
		
		Assert.assertFalse(context.isGameInProgress());
	}

	
	@Test 
	public void testCreateGameContextWithGameAndGameIsInProgress() {
	
		GameContext context = new GameContext();
		
		Team team1 = new Team("team1");
		Team team2 = new Team("team2");
		
		Game game = new Game(team1, team2);
		
		context.setGame(game);
		
		Assert.assertTrue(context.isGameInProgress());
	}
}
