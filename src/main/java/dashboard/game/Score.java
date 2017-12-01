package dashboard.game;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The Score class represents all the points accumulated by a Team.
 * 
 * @author sebastienhamel
 *
 */
public class Score {

	private List<Point> points;
	
	Score() {
		
		this.points = new ArrayList<Point>();
	}

	/**
	 * Method to add a new point instance to the Score.
	 * 
	 * @param point
	 */
	public void addPoint(Point point) {
		
		this.points.add(point);
	}
	
	/**
	 * Method that the number of points in this score instance. 
	 * 
	 * @return The number of points.
	 */
	public int count() {
		
		return points.size();
	}
	
	@Override
	public String toString() {
		
		Comparator<Point> c = (p, o) ->
	    	p.getMinute().compareTo(o.getMinute());
	 
	    points.sort(c);
	    
	    return points.stream().map(e -> e.toString()).collect(joining (" "));
	}
}
