package dashboard.parser;

import org.junit.Assert;
import org.junit.Test;

import dashboard.command.AddPointCommand;
import dashboard.command.Command;
import dashboard.command.EndGameCommand;
import dashboard.command.PrintGameScoreCommand;
import dashboard.command.StartGameCommand;

public class ParserTest {

	@Test 
	public void testParseStartCommand() throws ParseException {
		
		CommandParser commandParser = new CommandParser("Start: 'first team name' vs. 'second team name'");
		
		Command startCommand = commandParser.parse();
				
		StartGameCommand expectedCommand = new StartGameCommand("first team name", "second team name");
		
		Assert.assertEquals(expectedCommand, startCommand);
	}
	
	@Test 
	public void testParseStartCommandWithTwoTimesSameTeamNameShouldFail() {
		
		Boolean failed = true;
		
		try { 
		
			CommandParser commandParser = new CommandParser("Start: 'team name' vs. 'team name'");
			commandParser.parse();
		}
		catch (ParseException pe) {
			
			failed = false;
		}
		
		Assert.assertFalse(failed);
	}
	
	@Test 
	public void testParseAddPointCommand() throws ParseException {
		
		CommandParser commandParser = new CommandParser("11 'West Germany' Haller");
		
		Command addPointCommand = commandParser.parse();
				
		AddPointCommand expectedAddPointCommand = new AddPointCommand("11", "West Germany", "Haller");

		Assert.assertEquals(expectedAddPointCommand, addPointCommand);
	}
	
	@Test 
	public void testParseWrongPlayrNameAddPointCommand() throws ParseException {
		
		CommandParser commandParser = new CommandParser("11 'West Germany' Haller Wrong");
		
		Command addPointCommand = commandParser.parse();
				
		AddPointCommand expectedAddPointCommand = new AddPointCommand("11", "West Germany", "Haller Wrong");

		Assert.assertEquals(expectedAddPointCommand, addPointCommand);
	}
	
	@Test 
	public void testParseEndCommand() throws ParseException {
		
		CommandParser commandParser = new CommandParser("End");
		
		Command endCommand = commandParser.parse();
				
		EndGameCommand expectedEndCommand = new EndGameCommand();

		Assert.assertEquals(expectedEndCommand, endCommand);
	}
	
	@Test 
	public void testParsePrintCommand() throws ParseException {
		
		CommandParser commandParser = new CommandParser("print");
		
		Command printCommand = commandParser.parse();
				
		PrintGameScoreCommand expectedPrintGameScoreCommand = new PrintGameScoreCommand();

		Assert.assertEquals(expectedPrintGameScoreCommand, printCommand);
	}
	
	@Test 
	public void testParseCommandStartingWithDotShouldFail() {
		
		Boolean failed = false;
		
		try { 
		
			CommandParser commandParser = new CommandParser(".Start: 'team name' vs. 'team name'");
			commandParser.parse();
		}
		catch (ParseException pe) {
			
			failed = true;
		}
		
		Assert.assertTrue(failed);
	}
	
	@Test 
	public void testParseCommandStartingWithColonShouldFail() {
		
		Boolean failed = false;
		
		try { 
		
			CommandParser commandParser = new CommandParser(":Start: 'team name' vs. 'team name'");
			commandParser.parse();
		}
		catch (ParseException pe) {
			
			failed = true;
		}
		
		Assert.assertTrue(failed);
	}
	
	@Test 
	public void testParseStartCommandFollowedByDifferentThanColonShouldFail() {
		
		Boolean failed = false;
		
		try { 
		
			CommandParser commandParser = new CommandParser("Start  'team name' vs. 'team name'");
			commandParser.parse();
		}
		catch (ParseException pe) {
			
			failed = true;
		}
		
		Assert.assertTrue(failed);
	}
	
	@Test 
	public void testParseStartCommandVsWithoutDotShouldFail() {
		
		Boolean failed = false;
		
		try { 
		
			CommandParser commandParser = new CommandParser("Start  'team name' vs 'team name'");
			commandParser.parse();
		}
		catch (ParseException pe) {
			
			failed = true;
		}
		
		Assert.assertTrue(failed);
	}
	
	@Test 
	public void testParseStartCommandFollowedBySpacesShouldFail() {
		
		Boolean failed = false;
		
		try { 
		
			CommandParser commandParser = new CommandParser("Start    : 'team name' vs. 'team name'");
			commandParser.parse();
		}
		catch (ParseException pe) {
			
			failed = true;
		}
		
		Assert.assertTrue(failed);
	}
	
	@Test 
	public void testParseCommandStartingWithSingleQuoteShouldFail() {
		
		Boolean failed = false;
		
		try { 
		
			CommandParser commandParser = new CommandParser("'Start: 'team name' vs. 'team name'");
			commandParser.parse();
		}
		catch (ParseException pe) {
			
			failed = true;
		}
		
		Assert.assertTrue(failed);
	}
	
	@Test 
	public void testParseEmptyCommandShouldFail() {
		
		Boolean failed = false;
		
		try { 
		
			CommandParser commandParser = new CommandParser("");
			commandParser.parse();
		}
		catch (ParseException pe) {
			
			failed = true;
		}
		
		Assert.assertTrue(failed);
	}
}
