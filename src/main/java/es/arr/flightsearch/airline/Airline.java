package es.arr.flightsearch.airline;

public class Airline {
	private String IATACode;
	private String name;
	private float infantPrice;
	
	public Airline(String IATACode, String name, float infantPrice) {
		super();
		this.IATACode = IATACode;
		this.name = name;
		this.infantPrice = infantPrice;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getInfantPrice() {
		return infantPrice;
	}
	public void setInfantPrice(float infantPrice) {
		this.infantPrice = infantPrice;
	}

	public String getIATACode() {
		return IATACode;
	}

	public void setIATACode(String iATACode) {
		IATACode = iATACode;
	}
	
	@Override
	public String toString() {
		return "Airline [IATACode=" + IATACode + ", name=" + name + ", infantPrice=" + infantPrice +"]";
	}
	
}
