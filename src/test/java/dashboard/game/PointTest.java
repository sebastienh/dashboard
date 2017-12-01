package dashboard.game;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for Point.
 * 
 * @author sebastienhamel
 *
 */
public class PointTest {

	@Test 
	public void testCreatePoint() {
	
		Point point = new Point("test", Minute.CreateFromString("34"));
		
		Minute expectedMinute = Minute.CreateFromString("34");
		
		Assert.assertTrue(point.getMinute().equals(expectedMinute));
	}
}
