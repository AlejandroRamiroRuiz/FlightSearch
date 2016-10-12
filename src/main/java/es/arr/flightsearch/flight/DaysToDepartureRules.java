package es.arr.flightsearch.flight;

import java.util.HashMap;
import java.util.Iterator;

import es.arr.flightsearch.common.Range;

public class DaysToDepartureRules {
	
	private static HashMap<Range,Float> discountsPerRange=new HashMap<Range,Float>();
	
	// Recorrer las claves y ver qué rango se cumple. Ojo, con el primero vale.
	// No es responsabilidad de esta clase que los rangos sean coherentes.
	
	public static void addRule(Range range, float discount){
			
		discountsPerRange.put(range, discount);
	}
	
	public static void removeRule(Range range){
		discountsPerRange.remove(range);
	}
	
	public static void clearRules(){
		discountsPerRange.clear();
	}
	
	public static float getPriceModificator(int daysBefore){
		Iterator<Range> it = discountsPerRange.keySet().iterator();
		Range currentRange = null;
		float result = -1;
		
		while (it.hasNext()){
			currentRange = (Range) it.next();
			
			if (currentRange.evaluate(daysBefore)){
				result = discountsPerRange.get(currentRange);
			}
		}
		
		return result;
	}
}
