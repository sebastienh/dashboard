package dashboard.game;

import org.checkerframework.checker.igj.qual.ReadOnly;

/**
 * Class used to represent the concept of "point". 
 * A Point in a Game is scored by a player at a minute 
 * in a game. 
 * 
 * @author sebastienhamel
 *
 */
@ReadOnly
public class Point {
	
	private final String playerName;

	private final Minute minute;
	
	public Point(String playerName, Minute minute) {
		
		this.playerName = playerName;
		this.minute = minute;
	}

	public String getPlayerName() {
		return playerName;
	}

	public Minute getMinute() {
		return minute;
	}
	
	@Override
	public String toString() {
		
		return playerName + " " + minute + "'";
	}
}
