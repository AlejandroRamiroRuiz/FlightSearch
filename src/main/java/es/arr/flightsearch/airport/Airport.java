package es.arr.flightsearch.airport;

public class Airport {
	private String IATACode;
	private String cityName;
	
	public Airport(String iATACode, String cityName) {
		super();
		IATACode = iATACode;
		this.cityName = cityName;
	}
	
	public String getIATACode() {
		return IATACode;
	}
	public void setIATACode(String iATACode) {
		IATACode = iATACode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IATACode == null) ? 0 : IATACode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		if (IATACode == null) {
			if (other.IATACode != null)
				return false;
		} else if (!IATACode.equals(other.IATACode))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Airport [IATACode=" + IATACode + ", cityName=" + cityName + "]";
	}
}