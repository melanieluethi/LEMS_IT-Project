package ch.fhnw.lems.business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// done by HIS
// Source PLZ <--> Gemeinde-ID (Raw Data - transformation done by HIS): https://dam-api.bfs.admin.ch/hub/api/dam/assets/7226419/master
// Source Distance (Raw Data - transformation done by HIS): https://opendata.swiss/de/dataset/reisezeit-und-distanz-2017 

public class DistanceCalculation {
	public static Double calculateDistance(Integer inputPlz) {
		double deliveryDistance = 999.999;
		// please fill in the distance calculation

		// 1. get Input
		// 2. get gdeNr for input

		// 3. get distanze from file
		// read file
		// search for baseGdeNr
		// search for inputGdeNr
		// get distance

		// -define .csv file in app
		String fileName = "src/main/resources/static/data/DistancePLZ.CSV";
		Map<Integer, Double> distanceMap = new HashMap<Integer, Double>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			int iteration = 0;
			while ((line = br.readLine()) != null) {
				if (iteration == 0) {
					iteration++;
					continue;
				}
				String[] values = line.split(";");
				distanceMap.put(Integer.valueOf(values[0]), Double.valueOf(values[1]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(distanceMap.containsKey(inputPlz)) {
			deliveryDistance = distanceMap.get(inputPlz);	
		}
		return deliveryDistance;
	}
}