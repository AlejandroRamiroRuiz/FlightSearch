package es.arr.flightsearch.offer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import es.arr.flightsearch.airline.AirlineInfantPrices;
import es.arr.flightsearch.common.Constants;
import es.arr.flightsearch.common.DateUtils;
import es.arr.flightsearch.flight.DaysToDepartureRules;
import es.arr.flightsearch.flight.Flight;
import es.arr.flightsearch.flight.FlightCatalog;
import es.arr.flightsearch.passenger.PassengerTypeDiscount;

public  class OfferFinder {
	
	private static float calculatePrice(Flight flight, int numAdults, int numChildren, int numInfants, int daysToDeparture){
		
		float percentByDaysToDeparture = -1;
		float priceByDeparture		   = -1;
		float pricePerAdult		       = -1;
		float pricePerChild		       = -1;
		float pricePerInfant 		   = -1;
		
		float result 				   = -1;
		
		// Get percentage according to the days prior to departure
		percentByDaysToDeparture = DaysToDepartureRules.getPriceModificator(daysToDeparture);
		
		
		if (flight != null && percentByDaysToDeparture > 0){
			
			// Calculate price according days to departure rules
			priceByDeparture = flight.getBasePrice() * percentByDaysToDeparture/100;
			
			//System.out.println("[OfferFinder][calculatePrice]: Calculating price for flight, with priceByDeparture: " + priceByDeparture + " for fight [" + flight.getFlightCode() +"]");
			
			// Get infant price according to airline
			if (flight.getAirline() != null && priceByDeparture > 0){
				
				pricePerInfant = AirlineInfantPrices.getAirlineInfantPrice(flight.getAirline().getIATACode()) +
				priceByDeparture * PassengerTypeDiscount.getPassengerTypeDiscount(Constants.INFANT)/100; // This will be 0 in our case
				//System.out.println("[OfferFinder][calculatePrice]: pricePerInfant " +pricePerInfant );
				
				// Calculate price per adult
				pricePerAdult = priceByDeparture * (100 - PassengerTypeDiscount.getPassengerTypeDiscount(Constants.ADULT))/100;
				//System.out.println("[OfferFinder][calculatePrice]: pricePerAdult " + pricePerAdult );
				
				// Calculate price per child
				pricePerChild = priceByDeparture * (100 - PassengerTypeDiscount.getPassengerTypeDiscount(Constants.CHILD))/100;
				//System.out.println("[OfferFinder][calculatePrice]: pricePerChild " + pricePerChild );
			}
			
			// All is well-calculated, calculate total price
			if (pricePerInfant > 0 && pricePerAdult > 0 && pricePerChild > 0){
				result =  numAdults*pricePerAdult   + 
						  numChildren*pricePerChild +
						  numInfants*pricePerInfant;
			}
			else{
				System.out.println("[OfferFinder][calculatePrice]: Error calculating price for flight [" + flight.getFlightCode() +"]");
			}
		}
		
		return result;
	}
	
	
	public static List<Offer> searchFlights(String origin, String destiny, 
										   int numAdults, int numChildren, int numInfants, 
										   Date departureDate){
		
		List<Offer> offers = new ArrayList<Offer>();
		List<Flight> availableFlights = null;
		Flight currentFlight = null;
		Offer currentOffer = null;
		
		int daysToDeparture = DateUtils.daysToDate(departureDate);
		System.out.println("[OfferFinder][searchFlights]: Searching flights to fly from ["+ origin + "] to [" +  destiny + "] in [" + daysToDeparture + "] days");
		
		if (daysToDeparture >= 0){
			availableFlights = FlightCatalog.getFlightsByOrDest(origin, destiny);
			
			if (availableFlights != null && availableFlights.size() > 0){
				System.out.println("[OfferFinder][searchFlights]: Found [" + availableFlights.size() + "] available flights");
				Iterator<Flight> flightsIterator = availableFlights.iterator();
				
				while (flightsIterator.hasNext()){
					currentFlight = (Flight) flightsIterator.next();
					
					float calculatedPrice = calculatePrice(currentFlight, numAdults, numChildren, numInfants, daysToDeparture);
					
					if (calculatedPrice > 0 && currentFlight != null){
						currentOffer = new Offer(currentFlight.getFlightCode(),calculatedPrice);
						
						offers.add(currentOffer);
					}
					else{
						System.out.println("[OfferFinder][searchFlights]: Error calculating flight price [" + 
										     currentFlight.getFlightCode() + ", " + calculatedPrice +"]");
					}
				}
			}
			else{
				System.out.println("[OfferFinder][searchFlights]: No flights found");
			}
		}
		else{
			System.out.println("[OfferFinder][searchFlights]: Invalid departure date");
		}
		
		return offers;
	}
	
	public static void printOffers(List<Offer> offers){
		Iterator<Offer> offersIterator = null;
		Offer currentOffer = null;
		
		if (offers != null){
			offersIterator = offers.iterator();
			
			while (offersIterator.hasNext()){
				currentOffer = offersIterator.next();
				System.out.println(currentOffer);
			}
		
		}
	}
}
