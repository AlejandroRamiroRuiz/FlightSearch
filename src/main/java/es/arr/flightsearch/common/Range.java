package es.arr.flightsearch.common;

public class Range {
	private Bound upperBound;
	private Bound lowerBound;
	
	public Range(Bound lowerBound, Bound upperBound) {
		super();
		this.upperBound = upperBound;
		this.lowerBound = lowerBound;
	}
	
	public Bound getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(Bound upperBound) {
		this.upperBound = upperBound;
	}
	public Bound getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(Bound lowerBound) {
		this.lowerBound = lowerBound;
	}
	
	
	public boolean evaluate(int number){
		
		boolean result = false;
		
		if (this.lowerBound != null){
			
			result =this.lowerBound.evaluate(number);
			
		}
		else{
			result = true;
		}
		
		if (this.upperBound != null){
			result = result && this.upperBound.evaluate(number);
			
		}
		
		return result;
	}
	
	// todo -> Añadir el metodo HASH
}
