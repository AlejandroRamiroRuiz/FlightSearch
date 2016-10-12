package es.arr.flightsearch.passenger;

import java.util.HashMap;

public class PassengerTypeDiscount {
	
	private static HashMap<String,Float> passengerTypeDiscounts=new HashMap<String,Float>();
	
	
	
	public static float getPassengerTypeDiscount(String passengerType){
		float result = -1;
		
		if (passengerTypeDiscounts.containsKey(passengerType)){
			result = passengerTypeDiscounts.get(passengerType);
		}
		
		return result;
	}
	
	public static void addPassengerTypeDiscount(String passengerType, float discount){
		passengerTypeDiscounts.put(passengerType, discount);
	}
	
	public static void clearPassengerTyoeDiscounts(){
		passengerTypeDiscounts.clear();
	}
	
}
