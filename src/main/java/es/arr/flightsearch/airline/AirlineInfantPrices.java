package es.arr.flightsearch.airline;

import java.util.HashMap;

public class AirlineInfantPrices {
private static HashMap<String,Float> infantPrices=new HashMap<String,Float>();
	
	public static float getAirlineInfantPrice(String IATACode){
		float result = -1;
		
		if (infantPrices.containsKey(IATACode)){
			result = infantPrices.get(IATACode);
		}
		
		return result;
	}
	
	public static void setAirlineInfantPrice(String IATACode, float price){
		infantPrices.put(IATACode, price);
	}
	
	public static void clearPassengerTypesDiscount(){
		infantPrices.clear();
	}
}
