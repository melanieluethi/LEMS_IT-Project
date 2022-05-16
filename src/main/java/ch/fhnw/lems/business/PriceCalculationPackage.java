package ch.fhnw.lems.business;

//done by HiS
public class PriceCalculationPackage {
	private int testSumP1;
	private int testSumP2;
	private int testSumP3;
	private int testSumP4;
    
	
	private static int WEIGHT_MULTIPLICATOR_P1 = 20;
	private static int WEIGHT_MULTIPLICATOR_P2 = 60;
	private static int WEIGHT_MULTIPLICATOR_P3 = 58;
	private static int WEIGHT_MULTIPLICATOR_P4 = 2;


	// Grössen Multiplikator --> X Stück pro Palettenplatz
	private double spaceMultiplicatorP1 = 0.04;
	private double spaceMultiplicatorP2 = 0.1;
	private double spaceMultiplicatorP3 = 0.0666666;
	private double spaceMultiplicatorP4 = 0.01;
	
	private double spaceOfDelivery;
	private double weightOfDelivery;
	
	private double priceForDelivery = 55.0;
	
	
	// P1 max Stack:25 Min Space:1.2 weight: 25pc = 500kg --> 20kg
	// P2 max Stack:10 Min Space:2 weight: 10pc = 600kg --> 60kg
	// P3 max Stack:15 Min Space:2.5 weight 15pc = 870kg --> 58kg
	// P4 max Stack:100 Min Space:0.8 weight: 100pc = 200kg --> 2kg
	
	//if size & weight smaller than 1 Pallet-Stack & 550 KG --> offer Package price.
    //if not applicaple return false / "not applicable"
		
	public boolean packageOffer() {
		spaceOfDelivery = testSumP1 * spaceMultiplicatorP1 + testSumP2 * spaceMultiplicatorP2 + testSumP3 * spaceMultiplicatorP3 + testSumP4 * spaceMultiplicatorP4;
		weightOfDelivery = testSumP1 * WEIGHT_MULTIPLICATOR_P1 + testSumP2 * WEIGHT_MULTIPLICATOR_P2 + testSumP3 * WEIGHT_MULTIPLICATOR_P3 + testSumP4 * WEIGHT_MULTIPLICATOR_P4;
		
		if(spaceOfDelivery <= 1.0 && weightOfDelivery <= 550.0) {
			return true;
		} else {
			return false;
		}			
	}
	
	public double getPriceForDelivery() {
		return priceForDelivery;
	}

	public void setPriceForDelivery(double priceForDelivery) {
		this.priceForDelivery = priceForDelivery;
	}
}