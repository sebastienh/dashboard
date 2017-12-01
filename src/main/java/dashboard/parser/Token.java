package dashboard.parser;

import org.checkerframework.checker.igj.qual.ReadOnly;

/**
 * Class used to describe a "token" that 
 * can be found in a command. Each Token has a type,
 * and a String value.
 * 
 * @author sebastienhamel
 *
 */
@ReadOnly
public class Token {

	private final String value;
	
	private final TokenType type;
	
	Token(String value, TokenType type) {
		
		this.value = value;
		this.type = type; 
	}

	public String getValue() {
		return value;
	}

	public TokenType getType() {
		return type;
	}
}
