package dashboard.cli;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for CommandLineInterpreter class.
 * 
 * @author sebastienhamel
 *
 */
public class CommandLineInterpreterTest {

	@Test
	public void testCommandLineInterpreterSuccessfully() {
		
		CommandLineInterpreter commandLineInterpreter = CommandLineInterpreter.Create();
		
		Assert.assertNotNull(commandLineInterpreter);
	}
	
}
