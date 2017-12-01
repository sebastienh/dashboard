package dashboard.parser;

import dashboard.command.AddPointCommand;
import dashboard.command.Command;
import dashboard.command.EndGameCommand;
import dashboard.command.PrintGameScoreCommand;
import dashboard.command.StartGameCommand;

/**
 * The CommandParser class responsible for parsing 
 * a String to create a command from it.
 * 
 * @author sebastienhamel
 *
 */
public class CommandParser {

//	private final Logger parserLogger = LoggerFactory.getLogger(CommandParser.class);
	
	private final CommandScanner scanner;
	
	public CommandParser(String command) {
		
		this.scanner = new CommandScanner(command);
	}
	
	/**
	 * Method that parse a string using the CommandScanner.  
	 * 
	 * @return a Command to be executed.
	 */
	public Command parse() throws ParseException {
	
		Command command = null;
		
		Token token = scanner.scan();
		
		switch (token.getType()) {

		case STRING:

			// could be a "Start:" command an "End" command or 
			// a "print" command.  
			command = constructStartEndOrPrintCommand(token); 
			break;

		case END:

			// not acceptable input should throw an error
			throw new ParseException();

		case NUMBER:

			// we should attempt to construct an AddPointCommand.
			command =  constructAddPointCommand(token);
			break;

		case SINGLE_QUOTE:

			// not acceptable input should throw an error
			throw new ParseException();

		case COLON:

			// not acceptable input should throw an error
			throw new ParseException();
			
		case DOT:

			// not acceptable input should throw an error
			throw new ParseException();		
		}
		
		return command;
	}
	
	/**
	 * Method that construct a StartGameCommand, EndGameCommand or PrintGameScore command.
	 * 
	 * @param stringToken
	 * @return The constructed Command. 
	 */ 
	private Command constructStartEndOrPrintCommand(Token stringToken) throws ParseException {
		
		if (stringToken.getValue().equals(CommandString.START)) {
			
			return constructStartGameCommand();
		}
		else if (stringToken.getValue().equals(CommandString.END)) {
			
			return constructEndGameCommand();
		}
		else if (stringToken.getValue().equals(CommandString.PRINT)) {
			
			return constructPrintGameScoreCommand();
		}
		else {
			
			throw new ParseException();
		}
	} 
	
	/**
	 * Method that constructs a StartGameCommand.
	 * 
	 * "Start: '<Name of Home Team>' vs. '<Name of Away
	 *
	 * e.g. "Start: 'England' vs. 'West Germany'"
	 * 
	 * @return The constructed StartGameCommand.
	 * @throws ParseException
	 */
	private StartGameCommand constructStartGameCommand() throws ParseException {
		
		Token token = scanner.scan();
		
		if (token.getType() != TokenType.COLON) {
			
			throw new ParseException();
		}
		
		String homeTeamName = parseTeamQuotedName();
		
		parseVs();
		
		parseDot();
		
		String awayTeamName = parseTeamQuotedName();

		if(homeTeamName.equals(awayTeamName)) {
			
			throw new ParseException();
		}
		
		return new StartGameCommand(homeTeamName, awayTeamName);
	}
	
	/**
	 * Methods that parse the "vs" part of the "vs." command.
	 * 
	 * @throws ParseException
	 */
	private void parseVs() throws ParseException {
		
		Token token = scanner.scan();
		
		if (token.getType() != TokenType.STRING) {
			
			throw new ParseException();
		}
		else if (!token.getValue().equals(CommandString.VS)) {
				
			throw new ParseException();
		}
	}
	
	/**
	 * Methods that parse the "." part of the "vs." command.
	 * 
	 * @throws ParseException
	 */
	private void parseDot() throws ParseException {
		
		Token token = scanner.scan();
		
		if (token.getType() != TokenType.DOT) {
			
			throw new ParseException();
		}
		else if (!token.getValue().equals(".")) {
				
			throw new ParseException();
		}
	}
	
	/**
	 * Method that constructs a PrintGameScoreCommand.
	 * 
	 * @return The constructed PrintGameScoreCommand. 
	 * @throws ParseException
	 */
	private PrintGameScoreCommand constructPrintGameScoreCommand() throws ParseException {
		
		validateNoTokensLeft();
		
		return new PrintGameScoreCommand();
	}
	
	/**
	 * Method that constructs an EndGameCommand.
	 * 
	 * @return The constructed EndGameCommand.
	 * @throws ParseException
	 */
	private EndGameCommand constructEndGameCommand() throws ParseException {
		
		validateNoTokensLeft();
		
		return new EndGameCommand();
	}
	
	/**
	 * Method that validates there is nothing left after the current token.
	 * It simply throws a ParseException when this is not the case.
	 * 
	 * @throws ParseException
	 */
	private void validateNoTokensLeft() throws ParseException {
		
		Token token = scanner.scan();
		
		if (token.getType() != TokenType.END) {
			
			throw new ParseException();
		}
	}
	
	/**
	 * Method that construct an AddPointCommand.
	 * 
	 * @param numberToken
	 * @return The constructed AddPointCommand
	 */
	private AddPointCommand constructAddPointCommand(Token numberToken) throws ParseException {
		
		String teamName = parseTeamQuotedName();
		
		String scorerName =  parseNameOfScorer();
		
		return new AddPointCommand(numberToken.getValue(), teamName, scorerName);
	}
	
	/**
	 * Method that parse String with format: '<Team>' which in terms 
	 * of Tokens returned from the CommandScanner would be represented 
	 * as <SINGLE_QUOTE><STRING><SINGLE_QUOTE>.
	 * 
	 * @return
	 * @throws ParseException
	 */
	private String parseTeamQuotedName() throws ParseException {
		
		Token token = scanner.scan();
		
		if (token.getType() == TokenType.SINGLE_QUOTE) {
			
			Token stringToken = scanner.scan();
			
			if (stringToken.getType() == TokenType.STRING) {
				
				token = scanner.scan();
				
				if (token.getType() == TokenType.SINGLE_QUOTE) {
					
					// we have a well formed Single Quoted String
					return stringToken.getValue();
				}
				else {
				
					// missing ending single quote
					throw new ParseException();
				}
			}
			else {
				
				// expected string token
				throw new ParseException();
			}
		}
		else {
			
			// expected single quote token.
			throw new ParseException();
		}
	}
	
	/**
	 * Method that parse string with format <name of scorer> which in terms 
	 * of Tokens returned from the CommandScanner would be represented as <String>
	 * 
	 * @return
	 * @throws ParseException
	 */
	private String parseNameOfScorer() throws ParseException {
		
		Token token = scanner.scan();
		
		if (token.getType() == TokenType.STRING) {
			
			return token.getValue();
		}
		else {
			
			// expected string token
			throw new ParseException();
		}
	}
	
}
