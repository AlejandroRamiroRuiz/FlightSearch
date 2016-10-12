package test;

//import org.junit.Assert.*;
import org.junit.Test;
import org.junit.Assert;

import java.util.Date;
import java.util.List;


import es.arr.flightsearch.common.DateUtils;
import es.arr.flightsearch.offer.*;

public class FlightSearchTest extends Base{
	
	@Override
	protected void setUp() throws Exception{
		super.setUpBaseClass();
	}
	
	// 1 adult, 30 days to the departure date, flying AMS -> FRA
	@Test
	public void test1(){
		
		List<Offer> offers = OfferFinder.searchFlights("AMS", //Origin
									  "FRA", //Destiny
									  1,     // Adults
									  0,	 // Childre
									  0,     // Infant
									  DateUtils.getFutureDate(30)
									 );
									 
		OfferFinder.printOffers(offers);
		
		try{
			Assert.assertEquals(offers.size(), 3); // This test should find 3 available flights
		}
		catch (Exception e){
			fail(); 
		}
		
	}
	
	// 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST
	@Test
	public void test2(){
		
		List<Offer> offers = OfferFinder.searchFlights("LHR", //Origin
										  "IST", //Destiny
										  2,     // Adults
										  1,	 // Childre
										  1,     // Infant
										  DateUtils.getFutureDate(15)
										 );
										 
		OfferFinder.printOffers(offers);
		
		try{
			Assert.assertEquals(offers.size(), 2); // This test should find 2 available flights
		}
		catch (Exception e){
			fail();
		}
		
	}
	
	// 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD
	@Test
	public void test3(){
		
		List<Offer> offers = OfferFinder.searchFlights("BCN", //Origin
									  "MAD", //Destiny
									  1,     // Adults
									  2,	 // Childre
									  0,     // Infant
									  DateUtils.getFutureDate(2)
									 );
									 
		OfferFinder.printOffers(offers);
		
		try{
			Assert.assertEquals(offers.size(), 2); // This test should find 2 available flights
		}
		catch (Exception e){
			fail();
		}
		
	}
	
	// CDG -> FRA
	@Test
	public void test4(){
		
		List<Offer> offers = OfferFinder.searchFlights("CDG", //Origin
									  "FRA", //Destiny
									  1,     // Adults
									  2,	 // Childre
									  0,     // Infant
									  DateUtils.getFutureDate(2)
									 );
									 
		OfferFinder.printOffers(offers);
		
		try{
			Assert.assertEquals(offers.size(), 0); // This test should find 0 available flights
		}
		catch (Exception e){
			fail();
		}
			
		
	}
	
	@Override
	protected void tearDown() throws Exception{
		super.tearDownBaseClass();
	}
}