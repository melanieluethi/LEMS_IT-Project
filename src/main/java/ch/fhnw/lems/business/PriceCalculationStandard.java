package ch.fhnw.lems.business;

import ch.fhnw.lems.entity.TransportCost;
import ch.fhnw.lems.persistence.TransportCostRepository;

// HIS
public class PriceCalculationStandard {	
	double standardPrice;
	double standardDistance;
	int neededTrucks;
	int pallettCounter;
	long deliveryDistanceId;
	
    public double calculateStandardPrice(TransportCostRepository transportCostRepository, double distance, double pallett) {    	
    	standardDistance = Math.ceil(distance / 30);
    	deliveryDistanceId = (long) standardDistance;
    	pallettCounter = (int) Math.ceil(pallett);
        
        if(pallettCounter <= 12) {        	
        	//get price for palletts for one delivery
        	standardPrice = getPallett(transportCostRepository, pallettCounter);
        	
        } else {        	
        	while(pallettCounter > 0 && pallettCounter <= 12) {
            	neededTrucks ++;            	
            	if (pallettCounter > 12) {
            		//get Price for 12 paletts at the given distance
            		standardPrice += getPallett(transportCostRepository, 12);              		
            	}else {
            		//get price for the rest of the palletts at the given distance
            		standardPrice += getPallett(transportCostRepository, pallettCounter); 
            	}
            	pallettCounter = pallettCounter - 12;	
            }        	
        }
    	return standardPrice;
    }
	
    private Double getPallett(TransportCostRepository transportCostRepository, int pallett) {
    	Long transportId = Long.valueOf(pallett);
    	TransportCost transportCost = transportCostRepository.findById(transportId).get();
		double pallet = 0.0d;
		switch (pallett) {
			case 1:
				pallet = transportCost.getPallet1();
				break;
			case 2: 
				pallet = transportCost.getPallet2();
				break;
			case 3:
				pallet = transportCost.getPallet3();
				break;
			case 4: 
				pallet = transportCost.getPallet4();
				break;
			case 5:
				pallet = transportCost.getPallet5();
				break;
			case 6: 
				pallet = transportCost.getPallet6();
				break;
			case 7:
				pallet = transportCost.getPallet7();
				break;
			case 8: 
				pallet = transportCost.getPallet8();
				break;
			case 9:
				pallet = transportCost.getPallet9();
				break;
			case 10: 
				pallet = transportCost.getPallet10();
				break;
			case 11:
				pallet = transportCost.getPallet11();
				break;
			case 12: 
				pallet = transportCost.getPallet12();
				break;
			default:
				break;
		}
		return pallet;
	}	
}