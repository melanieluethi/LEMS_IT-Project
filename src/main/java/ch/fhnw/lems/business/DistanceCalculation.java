package ch.fhnw.lems.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// done by HIS
// Source PLZ <--> Gemeinde-ID (Raw Data - transformation done by HIS): https://dam-api.bfs.admin.ch/hub/api/dam/assets/7226419/master
// Source Distance (Raw Data - transformation done by HIS): https://opendata.swiss/de/dataset/reisezeit-und-distanz-2017 

public class DistanceCalculation {

	private int inputPlz = 3097; // 3097 as a Test-Value
	private int inputGdeNr;
	private int basePlz = 4600; // PLZ from Olten
	private int baseGdeNr = 2581; // GdeNr from Olten
	private double deliveryDistance;

    public static double calculateDistance() {

    	
    	
    	// please fill in the distance calculation
    	
    	
    	// 1. get Input
    	// 2. get gdeNr for input
    	
    	// 3. get distanze from file
    		// read file
    		// search for baseGdeNr
    		// search for inputGdeNr
    		// get distance
    	
        // -define .csv file in app
        String fileName = "Distance.CSV";
        // -File class needed to turn stringName to actual file
        File file = new File(fileName);
    	
    	
		return 0;
    	
    }

    
    //
    /*try{
        
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String data = scanner.next();
            System.out.println(data + "***");

        }
        scanner.close();
    }catch (FileNotFoundException e){

        e.printStackTrace();
    }
     
     * 
     */
     
	
    
	
	
	
	
	
	
	
	
}
