package dashboard.parser;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.common.base.CharMatcher;

/**
 * Class responsible for scanning a command in a String 
 * form and return an array of tokens representing the command.
 * 
 * @author sebastienhamel
 *
 */
public class CommandScanner {
	
	private final String commandString;
	
	// 
	private int position;
	
	/*
	 * Variable to keep the state we are in, if we are in a 
	 * single quote we accept everything as a String until we find 
	 * a not escaped final single quote.  
	 */
	private @NonNull Boolean insideSingleQuote;

	private @NonNull Boolean endOfQuotedString;
	
	CommandScanner(String commandString) {
		
		this.commandString = commandString;
		this.insideSingleQuote = false;
		this.endOfQuotedString = false;
		this.position = 0;
	}
	
	public @NonNull Token scan() {
		
		Token token = null;
		
		if (position >= commandString.length()) {
			
			return new Token("", TokenType.END);
		}
		
		if (insideSingleQuote) {
			
			token = scanQuotedString();
		}
		else if (endOfQuotedString) {
			
			char character = commandString.charAt(position++);
			
			if (isSingleQuote(character)) {
				
				endOfQuotedString = false;
				
				token = new Token("'", TokenType.SINGLE_QUOTE);	
			}
		}
		else {
		
			skipWhitespaces();
			
			if (position >= commandString.length()) {
				
				return new Token("", TokenType.END);
			}
			
			char character = commandString.charAt(position++);
			
			if (isSingleQuote(character)) {
				
				insideSingleQuote = true;
				token = new Token("'", TokenType.SINGLE_QUOTE);	
			}
			else if (isDot(character)) {
				
				token = new Token(".", TokenType.DOT);
			}
			else if (isColon(character)) {
				
				token = new Token(":", TokenType.COLON);	
			}
			else if (isDigit(character)) {
			
				// return to the start of the number
				position--;
				token = scanNumber();
			}
			else if (isLetter(character)) {
			
				position--;
				token = scanUnquotedString();
			}
		}
		
		return token;
	}

	/**
	 * Method that scan an unquoted string. An unquoted string
	 * starts with a letter and ends with a dot, colon, end of input or a single quote 
	 * (to allow command without spaces).
	 * 
	 * @return
	 */
	private Token scanUnquotedString() {
		
		String scannedString = "";
		
		try {
		
			char character = commandString.charAt(position++);
			
			while(true) {
			
				if (isDot(character)) {
					
					position--;
					break;
				}
				else if (isSingleQuote(character)) {
					
					position--;
					break;
				}
				else if (isColon(character)) {
					
					position--;
					break;
				}
				else {
					
					scannedString += character;
				}
				
				character = commandString.charAt(position++);
			}
		} catch(IndexOutOfBoundsException e) {
			
			// nothing to do, we will just return the scannedString 
			// so far and the next time the CommandScanner will be called 
			// it will just return the END token.
		}
		
		return new Token(scannedString, TokenType.STRING);
	}
	
	/**
	 * This method scans a quoted string, meaning that we have 
	 * already encountered the first single quote and we proceed
	 * with scanning until we find the next unescaped single quote.
	 * 
	 * @return A Token of type "quoted string"
	 */
	private Token scanQuotedString() {
		
		String scannedString = "";
		
		try {
		
			char character = commandString.charAt(position++);
			
			while(true) {
			
				if (isSingleQuote(character)) {
			
					// we encountered the final single quote
					insideSingleQuote = false;
					endOfQuotedString = true;
					position--;
					break;
				}
				else {
					
					scannedString += character;
				}
				
				character = commandString.charAt(position++);
			}
		} catch(IndexOutOfBoundsException e) {
			
			// nothing to do, we will just return the scannedString 
			// so far and the next time the CommandScanner will be called 
			// it will just return the END token.
		}
		
		return new Token(scannedString, TokenType.STRING);
	}
		
	/**
	 * Method that scan a number, a number must be ended 
	 * by a space or a single quote. Any other endings 
	 * are not accepted.
	 * 
	 * @return A Number Token.
	 */
	private Token scanNumber() {
		
		String scannedString = "";
		
		try {
		
			char character = commandString.charAt(position++);
			
			while(true) {
			
				if (isDigit(character)) {
				
					scannedString += character;
				}
				else if (isSingleQuote(character) || isWhitespace(character)) {
					
					position--;
					break;
				}
				
				character = commandString.charAt(position++);
			}
		} catch(IndexOutOfBoundsException e) {
			
			// nothing to do, we will just return the scannedString 
			// so far and the next time the CommandScanner will be called 
			// it will just return the END token.
		}
		
		return new Token(scannedString, TokenType.NUMBER);
	}
	
	/**
	 * Method used to skip whitespaces.
	 * 
	 */
	private void skipWhitespaces() {
		
		try {
		
			char character = commandString.charAt(position++);
			
			while(true) {
			
				if (!isWhitespace(character)) {
				
					position--;
					break;
				}
				
				character = commandString.charAt(position++);
			}
		} catch(IndexOutOfBoundsException e) {
			
			// nothing to do, the next time the CommandScanner will 
			// be called it will just return the END token.
		}
	}

	/**
	 * Method that evaluates if a character is a single quote 
	 * or not and return true or false depending on the result.
	 * 
	 * 
	 * @param character
	 * @return true if character is a single quote , false otherwise.
	 */
	private Boolean isSingleQuote(char character) {
		
		if (character == '\'') {
			
			return true; 
		}
		
		return false;
	}
	
	/**
	 * Method that evaluates if a character is a whitespace 
	 * or not and return true or false depending on the result.
	 * 
	 * 
	 * @param character
	 * @return true if character is a whitespace, false otherwise.
	 */
	private Boolean isWhitespace(char character) {
		
		if (CharMatcher.BREAKING_WHITESPACE.matches(character)) {
			
			return true; 
		}
		
		return false;
	}
	
	/**
	 * Method that evaluates if a character is a letter 
	 * or not and return true or false depending on the result.
	 * 
	 * @param character
	 * @return true if character is a letter, false otherwise.
	 */
	private Boolean isLetter(char character) {
		
		if (CharMatcher.JAVA_LETTER.matches(character)) {
			
			return true; 
		}
		
		return false;
	}
	
	/**
	 * Method that evaluates if a character is a dot
	 * or not and return true or false depending on the result.
	 * 
	 * @param character
	 * @return true if character is a dot, false otherwise.
	 */
	private Boolean isDot(char character) {
		
		if (character == '.') {
			
			return true; 
		}
		
		return false;
	}
	
	/**
	 * Method that evaluates if a character is a digit [0-9]
	 * or not and return true or false depending on the result.
	 * 
	 * @param character
	 * @return true if character is a digit, false otherwise.
	 */
	private Boolean isDigit(char character) {
		
		if (CharMatcher.DIGIT.matches(character)) {
			
			return true; 
		}
		
		return false;
	}
	
	/**
	 * Method that evaluates if a character is a colon ":" 
	 * or not and return true or false depending on the result.
	 * 
	 * 
	 * @param character
	 * @return true if character is a colon, false otherwise.
	 */
	private Boolean isColon(char character) {
		
		if (character == ':') {
			
			return true; 
		}
		
		return false;
	}
	
	public String getCommandString() {
		return commandString;
	}
	
}
