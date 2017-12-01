package dashboard.game;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for Team class.
 * 
 * @author sebastienhamel
 *
 */
public class TeamTest {

	
	@Test 
	public void testTeamEqualityTrueSame() {
		
		Team team1 = new Team("name2");
		
		Assert.assertTrue(team1.equals(team1));
	}
	
	@Test 
	public void testTeamEqualityTrue() {
		
		Team team1 = new Team("name2");
		Team team2 = new Team("name2");
		
		Assert.assertTrue(team1.equals(team2));
	}

	@Test 
	public void testTeamEqualityFalse() {
		
		Team team1 = new Team("name2");
		Team team2 = new Team("name21");
		
		Assert.assertFalse(team1.equals(team2));
	}
	
	@Test 
	public void testTeamEqualityFalseNull() {
		
		Team team1 = new Team("name2");
		
		Assert.assertFalse(team1.equals(null));
	}
	
	@Test 
	public void testTeamEqualityFalseDifferentObject() {
		
		Team team1 = new Team("name2");
		
		Assert.assertFalse(team1.equals("33"));
	}
}
