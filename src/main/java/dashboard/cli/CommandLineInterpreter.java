package dashboard.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dashboard.command.Command;
import dashboard.game.GameContext;
import dashboard.parser.CommandParser;
import dashboard.parser.ParseException;

/**
 * This class manage user inputs.
 * 
 * @author sebastienhamel
 *
 */
public class CommandLineInterpreter {
	
	private GameContext context;
	
	/**
	 * Factory creation method.
	 * 
	 * @return  A new CommandLineInterpreter instance.
	 */
	public static CommandLineInterpreter Create() {
			
		return new  CommandLineInterpreter();
	}
	
	private CommandLineInterpreter() {
		
		this.context = new GameContext();
	}
	
	/**
	 * Main entry point of a game.
	 */
	public void start() {
		
		printMessageToConsole(Messages.WELCOME_MESSAGE);
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
		try {
			
			while(true) {
			
				String commandString = bufferedReader.readLine();
				
				try {
					
					CommandParser parser = new CommandParser(commandString);
					
					Command command = parser.parse();
					
					ConsoleMessage consoleMessage = command.execute(context);
					
					if(!consoleMessage.isEmpty()) {
					
						printMessageToConsole(consoleMessage.getMessage());
					}
				}
				catch (ParseException pe) {
					
					if(!context.isGameInProgress()) {
						
						printMessageToConsole(Messages.INPUT_ERROR_WHILE_GAME_NOT_IN_PROGRESS);
					}
					else {
						
						printMessageToConsole(Messages.INPUT_ERROR_WHILE_GAME_IN_PROGRESS);
					}
				}
			}
		}
		catch(IOException io) {
			
			// print an error message and exit 
			System.out.println("An error occured: " + io.getMessage());
			
			System.exit(1);
		}
	}
	
	/**
	 * Method that prints a message to the console.
	 * 
	 * @param message
	 */
	private void printMessageToConsole(String message) {
		
		System.out.println(message);
	}
}
