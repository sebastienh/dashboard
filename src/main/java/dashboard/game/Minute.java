package dashboard.game;

/**
 * Minute represent a moment in a game. A Game 
 * starts at minute 1. The concept of Minute is internally
 * represented as an Integer.  
 * 
 * @author sebastienhamel
 *
 */
public class Minute implements Comparable<Minute> {

	private final Integer value;
	
	public static Minute CreateFromString(String minuteString) {
		
		Integer integerValue = Integer.parseInt(minuteString);
		
		return new Minute(integerValue);
	}
	
	public Minute(Integer value) {
		
		this.value = value;
	}

	@Override
	public boolean equals(Object other) {
		
		if (other == null) {
			return false;
		}
	
		if (other == this) {
			return true;
		}
		  
		if (!(other instanceof Minute)){
			return false;
		}
		
		Minute otherMinute = (Minute)other;
		    
		if(!this.value.equals(otherMinute.getValue())) {
			
			return false;
		}
		
		return true;
	}
	
	@Override
	public int compareTo(Minute o) {
		
		return value.compareTo(o.getValue());
	}
	
	@Override
	public String toString() {
		
		return value.toString();
	}
	
	public Integer getValue() {
		return value;
	}
}
