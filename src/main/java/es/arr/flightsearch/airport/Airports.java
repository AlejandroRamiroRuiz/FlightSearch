package es.arr.flightsearch.airport;

import java.util.HashMap;

public class Airports {
	private static HashMap<String,String> airports=new HashMap<String,String>();
	
	public static String getAirportCity(String IATACode){
		String result = "";
		
		if (airports.containsKey(IATACode)){
			result = airports.get(IATACode);
		}
		
		return result;
	}
	
	public static void addAirport(String IATACode, String city){
		airports.put(IATACode, city);
	}
	
	public static void clearAirports(){
		airports.clear();
	}
}

