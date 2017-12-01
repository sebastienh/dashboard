package dashboard.game;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for Score class.
 * 
 * @author sebastienhamel
 *
 */
public class ScoreTest {

	@Test 
	public void testAddOnePoint() {
		
		Score score = new Score();
		Point point = new Point("test", Minute.CreateFromString("34"));
		
		score.addPoint(point);
		
		Assert.assertTrue(score.count() == 1);
	}
	
	@Test 
	public void testAddTwoPointsSamePlayerSameMinute() {
		
		Score score = new Score();
		Point point1 = new Point("test", Minute.CreateFromString("34"));
		Point point2 = new Point("test", Minute.CreateFromString("34"));
		
		score.addPoint(point1);
		score.addPoint(point2);
		
		Assert.assertTrue(score.count() == 2);
	}
	
	@Test 
	public void testAddTwoPointsSamePlayerSameMinuteToString() {
		
		Score score = new Score();
		Point point1 = new Point("test", Minute.CreateFromString("34"));
		Point point2 = new Point("test", Minute.CreateFromString("34"));
		
		score.addPoint(point1);
		score.addPoint(point2);
		
		String expectedString = "test 34' test 34'";
		
		Assert.assertTrue(expectedString.equals(score.toString()));
	}
	
}
