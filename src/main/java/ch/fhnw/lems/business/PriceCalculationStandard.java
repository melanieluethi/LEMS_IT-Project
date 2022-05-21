package ch.fhnw.lems.business;

public class PriceCalculationStandard {

	double standardPrice;
	int neededTrucks;
	int pallettCounter;
	long deliveryDistanceId;
	
    public double calculateStandardPrice(double distance, double pallett) {    	
        deliveryDistanceId = (long) distance / 30;
        pallettCounter = (int) Math.ceil(pallett);

        
        
        if(pallettCounter <= 12) {
        	
        	//get price for palletts for one delivery
        	
        }else {
        	
        	while(pallettCounter > 0 && pallettCounter <= 12) {
            	neededTrucks ++;
            	
            	if (pallettCounter >12) {
            		
            		
            		//get Price for 12 paletts at the given distance
            		
            	}else {
            		//get price for the rest of the palletts at the given distance
            	}

            	
            	pallettCounter = pallettCounter - 12;	
            }
        	
        	
        	
        }
        
        

    	
    	return standardPrice;
    }
	
	
	
	
}
