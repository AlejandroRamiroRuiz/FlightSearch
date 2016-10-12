package es.arr.flightsearch.flight;

import es.arr.flightsearch.airline.Airline;
import es.arr.flightsearch.airport.Airport;

public class Flight {
	
	private Airport origin;
	private Airport destiny;
	private Airline airline;
	private float price;
	private int code;
	

	public Flight(Airport origin, Airport destiny, Airline airline, float basePrice, int code) {
		super();
		this.origin = origin;
		this.destiny = destiny;
		this.airline = airline;
		this.price = basePrice;
		this.code = code;
	}
	
	public Airport getOrigin() {
		return origin;
	}
	public void setOrigin(Airport origin) {
		this.origin = origin;
	}
	public Airport getDestiny() {
		return destiny;
	}
	public void setDestiny(Airport destiny) {
		this.destiny = destiny;
	}
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	public float getBasePrice() {
		return price;
	}
	public void setBasePrice(float basePrice) {
		this.price = basePrice;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getFlightCode(){
		return this.airline.getIATACode() + this.code;
	}

	
	public String getIATAOriginDestiny(){
		
		String result = "";
		
		if (this.origin != null && this.origin.getIATACode() != null){
			result = this.origin.getIATACode();
		}
		
		if (this.destiny != null && this.destiny.getIATACode() != null){
			result = result + this.destiny.getIATACode();
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return "Flight [origin=" + origin.toString() + ", destiny=" + destiny.toString() + ", airline=" + airline.toString() + ", price=" + price
				+ ", code=" + code + "]";
	}
}
