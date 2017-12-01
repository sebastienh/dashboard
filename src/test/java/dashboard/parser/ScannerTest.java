package dashboard.parser;

import org.junit.Assert;
import org.junit.Test;

public class ScannerTest {

  @Test
  public void testScanUnquotedString() {
	  
	  String unquotedString = "stringwithoutquotes";
	  
	  CommandScanner scanner = new CommandScanner(unquotedString);
	  
	  Token token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.STRING);
	  
	  Assert.assertEquals(token.getValue(), unquotedString);
  }
	
  @Test 
  public void testScanQuotedString() {
	  
	  String quotedString = "'stringwithquotes'";
	  
	  CommandScanner scanner = new CommandScanner(quotedString);
	  
	  Token token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.SINGLE_QUOTE);
	  
	  Assert.assertEquals(token.getValue(), "'");
	  
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.STRING);
	  
	  Assert.assertEquals(token.getValue(), "stringwithquotes");
	  
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.SINGLE_QUOTE);
	  
	  Assert.assertEquals(token.getValue(), "'");
  }
  
  @Test 
  public void testScanNumber() {
	  
	  String numberString = "12345";
	  
	  CommandScanner scanner = new CommandScanner(numberString);
	  
	  Token token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.NUMBER);
	  
	  Assert.assertEquals(token.getValue(), numberString);
  }
  
  @Test 
  public void testScanEndAfterNumber() {
	  
	  String numberString = "12345";
	  
	  CommandScanner scanner = new CommandScanner(numberString);
	  
	  // should be the number 
	  Token token = scanner.scan();
	  
	  // should be the end
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.END);
  }
 
  @Test 
  public void testScanEndAfterSpace() {
	  
	  String numberString = "12345   ";
	  
	  CommandScanner scanner = new CommandScanner(numberString);
	  
	  // should be the number 
	  Token token = scanner.scan();
	  
	  // should be the end
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.END);
  }
  
  @Test 
  public void testScanEndAfterQuotedStringAndSpace() {
	  
	  String numberString = "'quotedString'   ";
	  
	  CommandScanner scanner = new CommandScanner(numberString);
	  
	  // should be the single quote 
	  Token token = scanner.scan();
	  
	  // should be the string
	  token = scanner.scan();
	  
	  // should be the single quote 
	  token = scanner.scan();
	  
	  // should be the end
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.END);
  }
  
  @Test 
  public void testScanStartCommand() {
	  
	  String numberString = "Start: 'first team name' vs. 'second team name'";
	  
	  CommandScanner scanner = new CommandScanner(numberString);
	  
	  // "Start: 'first team name' vs. 'second team name'"
	  //  ˆˆˆˆˆ
	  Token token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.STRING);
	  
	  Assert.assertEquals(token.getValue(), "Start");
	  
	  // "Start: 'first team name' vs. 'second team name'"
	  //       ˆ
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.COLON);
	  
	  Assert.assertEquals(token.getValue(), ":");
	  
	  // "Start: 'first team name' vs. 'second team name'"
	  //         ˆ
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.SINGLE_QUOTE);
	  
	  Assert.assertEquals(token.getValue(), "'");
	  
	  // "Start: 'first team name' vs. 'second team name'"
	  //          ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.STRING);
	  
	  Assert.assertEquals(token.getValue(), "first team name");
	  
	  // "Start: 'first team name' vs. 'second team name'"
	  //                         ˆ
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.SINGLE_QUOTE);
	  
	  Assert.assertEquals(token.getValue(), "'");
	  
	  // "Start: 'first team name' vs. 'second team name'"
	  //                           ˆˆ
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.STRING);
	  
	  Assert.assertEquals(token.getValue(), "vs");
	  
	  // "Start: 'first team name' vs. 'second team name'"
	  //                             ˆ
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.DOT);
	  
	  Assert.assertEquals(token.getValue(), ".");
	  
	  // "Start: 'first team name' vs. 'second team name'"
	  //                               ˆ 
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.SINGLE_QUOTE);
	  
	  Assert.assertEquals(token.getValue(), "'");
	  
	  // "Start: 'first team name' vs. 'second team name'"
	  //                                ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.STRING);
	  
	  Assert.assertEquals(token.getValue(), "second team name");
	  
	  // "Start: 'first team name' vs. 'second team name'"
	  //                                                ˆ
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.SINGLE_QUOTE);
	  
	  Assert.assertEquals(token.getValue(), "'");
	  
	  // should be the end
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.END);
  }
  
  @Test 
  public void testScanPointAddition() {
	  
	  String numberString = "11 'West Germany' Haller";
	  
	  CommandScanner scanner = new CommandScanner(numberString);
	  
	  // 11 'West Germany' Haller
	  // ˆˆ
	  Token token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.NUMBER);
	  
	  Assert.assertEquals(token.getValue(), "11");
	  
	  // 11 'West Germany' Haller
	  //    ˆ
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.SINGLE_QUOTE);
	  
	  Assert.assertEquals(token.getValue(), "'");
	  
	  // 11 'West Germany' Haller
	  //     ˆˆˆˆˆˆˆˆˆˆˆˆ
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.STRING);
	  
	  Assert.assertEquals(token.getValue(), "West Germany");
	  
	  // 11 'West Germany' Haller
	  //                 ˆ 
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.SINGLE_QUOTE);
	  
	  Assert.assertEquals(token.getValue(), "'");
	  
	  // 11 'West Germany' Haller
	  //                   ˆˆˆˆˆˆ
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.STRING);
	  
	  Assert.assertEquals(token.getValue(), "Haller");
	  
	  // should be the end
	  token = scanner.scan();
	  
	  Assert.assertEquals(token.getType(), TokenType.END);
  }
  
  
}
