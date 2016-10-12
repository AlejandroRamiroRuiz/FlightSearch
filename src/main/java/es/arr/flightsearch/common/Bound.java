package es.arr.flightsearch.common;

public class Bound {
	private String comparator;
	private int amount;
	
	public Bound(String comparator, int amount) {
		super();
		this.comparator = comparator;
		this.amount = amount;
	}
	
	public String getComparator() {
		return comparator;
	}
	public void setComparator(String comparator) {
		this.comparator = comparator;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public boolean evaluate(int number){
		
		boolean resultado = false;
		
		switch(this.comparator){
							
			case Constants.EQ:
								resultado = (number == this.amount);
								break;
			case Constants.LT:
								resultado = (number < this.amount);
								break;
			case Constants.LE:
								resultado = (number <= this.amount);
								break;
			case Constants.GT:
								resultado = (number > this.amount);
								break;
			case Constants.GE:
								resultado = (number >= this.amount);
								break;
			default:
		}
		
		return resultado;
	}
	
}
