package test;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import es.arr.flightsearch.flight.*;
import es.arr.flightsearch.airline.AirlineInfantPrices;
import es.arr.flightsearch.common.*;
import es.arr.flightsearch.airport.*;
import es.arr.flightsearch.offer.*;
import es.arr.flightsearch.passenger.*;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStreamReader;  

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Base extends TestCase{
	@BeforeClass
    public static void setUpBaseClass() {
        // Set infant prices table
		AirlineInfantPrices.setAirlineInfantPrice("IB",10);
		AirlineInfantPrices.setAirlineInfantPrice("BA",15);
		AirlineInfantPrices.setAirlineInfantPrice("LH",7);
		AirlineInfantPrices.setAirlineInfantPrice("FR",20);
		AirlineInfantPrices.setAirlineInfantPrice("TK",5);
		AirlineInfantPrices.setAirlineInfantPrice("VY",5);
		AirlineInfantPrices.setAirlineInfantPrice("U2",new Float(19.90));
		
		// Set days to departure rules
		// 1) Set bounds
		Bound bGT30 = new Bound(Constants.GT, 30); // Greater than 30 days
        
        Bound bLE30 = new Bound(Constants.LE, 30); // Less or equal than 30 days
        Bound bGE16 = new Bound(Constants.GE, 16); // Greater or equal than 16 days
        
        Bound bLE15 = new Bound(Constants.LE, 15); // Less than 15 days
        Bound bGE3 = new Bound(Constants.GE, 3);   // Greater or equal than 3 days
        
        Bound bLT3 = new Bound(Constants.LT, 3);   // Greater or equal than 3 days
		
		// 2) Set ranges with previous bounds
		Range r1 = new Range(null,bGT30);
        Range r2 = new Range(bLE30,bGE16);
        Range r3 = new Range(bLE15,bGE3);
        Range r4 = new Range(bLT3,null);
		
		// 3) Associate prices to ranges
		DaysToDepartureRules.addRule(r1, 80);
        DaysToDepartureRules.addRule(r2, 100);
        DaysToDepartureRules.addRule(r3, 120);
        DaysToDepartureRules.addRule(r4, 150);
		
		// Set available airports
		Airports.addAirport("MAD","Madrid");
		Airports.addAirport("BCN","Barcelona");
		Airports.addAirport("LHR","London");
		Airports.addAirport("CDG","Paris");
		Airports.addAirport("FRA","Frakfurt");
		Airports.addAirport("IST","Istanbul");
		Airports.addAirport("FCO","Rome");
		Airports.addAirport("CPH","Copenhagen");
		Airports.addAirport("AMS","Amsterdam");
		
		// Set discounts per type of passenger
		PassengerTypeDiscount.addPassengerTypeDiscount(Constants.ADULT, 0); // Full price
		PassengerTypeDiscount.addPassengerTypeDiscount(Constants.CHILD, 33);  
		PassengerTypeDiscount.addPassengerTypeDiscount(Constants.INFANT, 0);  // No departure date rules
		
		// Set flights catalog
		FlightCatalog.parseFlights("flights.txt", ",");
    }
	
	// 1 adult, 30 days to the departure date, flying AMS -> FRA
	/*@Test
	public void test(){
		
	}*/
	
	@AfterClass
	public static void tearDownBaseClass() {
        // Clear infant prices table
		AirlineInfantPrices.clearPassengerTypesDiscount();
		
		// Clear airports
		Airports.clearAirports();
		
		// Clear days to departure rules
		DaysToDepartureRules.clearRules();
		
		// Clear Passenger typers discounts
		PassengerTypeDiscount.clearPassengerTyoeDiscounts();
		
		// Clear flight catalog
		FlightCatalog.clearFlights();
    }
}