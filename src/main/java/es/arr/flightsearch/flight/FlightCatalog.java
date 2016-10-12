package es.arr.flightsearch.flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStreamReader;  

import es.arr.flightsearch.common.ResourceProvider;

import es.arr.flightsearch.airline.*;
import es.arr.flightsearch.airport.*;

public class FlightCatalog {
	
	private static HashMap<String,List<Flight>> flights=new HashMap<String,List<Flight>>();
	
	public static List<Flight> getFlightsByOrDest(String IATA_Origin, String IATA_Destiny){
		
		ArrayList<Flight> flightsList = (ArrayList<Flight>)flights.get(IATA_Origin+IATA_Destiny);
		
		return flightsList;
	}
	
	public static void addFlight(Flight flight){
		List<Flight> list;
		
		if (flight.getIATAOriginDestiny() != ""){
			
			list = flights.get(flight.getIATAOriginDestiny());
			
			// There isn't still any flights for this origin-destiny combination
			if (list == null){
				list = new ArrayList<Flight>();
				flights.put(flight.getIATAOriginDestiny(), list);
			}
			
			if (!list.add(flight)){
				System.out.println("[fLIGHTcATALOG][addFlight] Error adding flight to [" + flight.getIATAOriginDestiny() + "] list");	
			}
		}
	}
	
	/*Create flight catalog parsing an input 
	 * Accepted format is: ORIGIN,DESTINTY,FLIGHTCODE,BASEPRICE
	 */
	public static void parseFlights(String dataFilePath, String separator){
		BufferedReader br = ResourceProvider.getParser(dataFilePath);
		String line = "";
		
		if (br != null){
			
			//System.out.println("Loading flights catalog from [" + dataFilePath + "]");
			try {		
				while ((line = br.readLine()) != null) {

					
					String[] flightTokens = line.split(separator);
					
					/*System.out.println("Origin  = "    + flightTokens[0] + ", "     + 
									   "Destiny ="     + flightTokens[1] + ", "       +
									   "Flight Code =" + flightTokens[2] + ", "   +
									   "Base Price ="  + flightTokens[3]
									  );*/
									  
					String IATACodeAirline = "";
					String flightNumberStr = "";
					String cityFrom = Airports.getAirportCity(flightTokens[0]);
					String cityTo   = Airports.getAirportCity(flightTokens[1]);
					int flightNumber = -1;
					float basePrice = -1;
					
					// Check the origin airport
					if (cityFrom == ""){
						System.out.println("[FlightCatalog][parseFlights]: Unkown origin Airport IATA code [" + flightTokens[0] +"]. Flight [" + 
											    flightTokens[2] + "] won't be added to the catalog");
						continue;
					}
					// Check de destiny airport
					else if (cityTo == ""){
						System.out.println("[FlightCatalog][parseFlights]: Unkown destiny Airport IATA code [" + flightTokens[1] +"]. Flight [" + 
											    flightTokens[2] + "] won't be added to the catalog");
						continue;
					}
					// Check flight code
					else if (flightTokens[2] != null && (flightTokens[2]).length() > 2){
						IATACodeAirline = (flightTokens[2]).substring(0,2);
						
						flightNumberStr = (flightTokens[2]).substring(2,(flightTokens[2]).length());
						
						if (AirlineInfantPrices.getAirlineInfantPrice(IATACodeAirline) < 0){
							System.out.println("[FlightCatalog][parseFlights]: Unkown Airline IATA code [" + IATACodeAirline +"]. Flight [" + 
											    flightTokens[2] + "] won't be added to the catalog");
							continue;
						}
						else if (flightNumberStr.length() != 4){
							
							System.out.println("[FlightCatalog][parseFlights]: Invalid flight number [" + flightNumber +"] Flight [" + 
											    flightTokens[2] + "] won't be added to the catalog");
							continue;
						}
						// Check flight number and base price
						else{
							
							try{
								flightNumber = Integer.parseInt(flightNumberStr);
							}catch(NumberFormatException e){
								System.out.println("[FlightCatalog][parseFlights]: Invalid flight number [" + flightNumber +"] Flight [" + 
											    flightTokens[2] + "] won't be added to the catalog");
								continue;
							}	
							
							try{
								basePrice = Float.parseFloat(flightTokens[3]);
								
							}catch(NumberFormatException e){
								System.out.println("[FlightCatalog][parseFlights]: Invalid flight price [" + flightTokens[3] +"] Flight [" + 
											    flightTokens[2] + "] won't be added to the catalog");
								continue;
							}
							
							addFlight(new Flight(new Airport(flightTokens[0],cityFrom), // Set origin
												 new Airport(flightTokens[1],cityTo), // Set destiny
												 new Airline(IATACodeAirline,
												 "",
												 AirlineInfantPrices.getAirlineInfantPrice(IATACodeAirline)),// Set airline
												 basePrice,
												 flightNumber
												));
						}
					}
				}
			
				br.close();
			} catch (IOException e) {
				System.out.println("[FlightCatalog][parseFlights]: Error parsing input file");
			}
		}
	}

	public static void clearFlights(){
		flights.clear();
	}
	 
	
}
