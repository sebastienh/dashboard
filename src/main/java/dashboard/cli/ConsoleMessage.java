package dashboard.cli;

import org.checkerframework.checker.igj.qual.ReadOnly;

/**
 * Class that encapsulates a String that should be printed to the console.
 * 
 * @author sebastienhamel
 *
 */
@ReadOnly
public class ConsoleMessage {

	private String message;
	
	public ConsoleMessage() {
		
		// empty constructor
	}
	
	public ConsoleMessage(String message) {
		
		this.message = message;
	}

	public Boolean isEmpty() {
		
		if(message == null) {
			
			return true;
		}
		
		return false;
	}
	
	public String getMessage() {
		return message;
	}
	
}
