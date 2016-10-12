package es.arr.flightsearch.offer;

public class Offer {
	private String flightCode;
	private float price;
	
	public Offer(String flightCode, float price) {
		super();
		this.flightCode = flightCode;
		this.price = price;
	}
	
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Offer [flightCode=" + flightCode + ", price=" + price + "]";
	}
	
}
