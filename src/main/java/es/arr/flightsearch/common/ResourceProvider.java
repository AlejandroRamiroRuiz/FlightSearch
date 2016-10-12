package es.arr.flightsearch.common;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStreamReader;  

public class ResourceProvider {
	
	/*
	 * Load the file in the given path and provide a reader
	*/
	public static BufferedReader getParser(String resourcePath){
		BufferedReader br = null;
		File dataFile = null;
		FileInputStream inputStream=null;
		
		try {
			// Getting ClassLoader obj  
			ClassLoader classLoader = ResourceProvider.class.getClassLoader();  
			
			// Getting resource(File) from class loader  
			dataFile=new File(classLoader.getResource(resourcePath).getFile());
			
			if (resourcePath != "" && dataFile != null){
				inputStream = new FileInputStream(dataFile); 
				
				if (inputStream != null){
					br = new BufferedReader(new InputStreamReader(inputStream));
					
					//System.out.println("Loading file [" + dataFile + "]");
					
				}
				else{
					System.out.println("[ResourceProvider][getParser]: Invalid file path or invalid file");
				}
			}
			else{
				System.out.println("[ResourceProvider][getParser]: Invalid file path or invalid file");
			}
        } catch (FileNotFoundException e) {
            System.out.println("[ResourceProvider][getParser]: Input file not found.");
        } catch (IOException e) {
            System.out.println("[ResourceProvider][getParser]: Error parsing input file");
        } 
		
		return br;
	}
}