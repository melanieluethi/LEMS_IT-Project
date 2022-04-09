package ch.fhnw.lems.business;

//done by HiS
public class SpaceCalculation {

	// Paletten-Platzbedarf wird IMMER Aufgerundet

	// max 300kg pro Palette
	// P1 max Stack:25 Min Space:1.2 weight: 25pc = 500kg --> 20kg
	// P2 max Stack:10 Min Space:2 weight: 10pc = 600kg --> 60kg
	// P3 max Stack:15 Min Space:2.5 weight 15pc = 870kg --> 58kg
	// P4 max Stack:100 Min Space:0.8 weight: 100pc = 200kg --> 2kg

	private int testSumP1;
	private int testSumP2;
	private int testSumP3;
	private int testSumP4;

	private int weightMultiplicatorP1 = 20;
	private int weightMultiplicatorP2 = 60;
	private int weightMultiplicatorP3 = 58;
	private int weightMultiplicatorP4 = 2;

	private int weightP1;
	private int weightP2;
	private int weightP3;
	private int weightP4;

	private int totalWeight;
	
	private double palletSpaceP1;
	private double palletSpaceP2;
	private double palletSpaceP3;
	private double palletSpaceP4;

	//calculate the weight per Product
	public double calculateWight() {

		if (testSumP1 != 0) {
			weightP1 = testSumP1 * weightMultiplicatorP1;
		}

		if (testSumP2 != 0) {
			weightP2 = testSumP2 * weightMultiplicatorP2;
		}

		if (testSumP3 != 0) {
			weightP3 = testSumP3 * weightMultiplicatorP3;
		}

		if (testSumP4 != 0) {
			weightP4 = testSumP4 * weightMultiplicatorP4;
		}

		totalWeight = weightP1 + weightP2 + weightP3 + weightP4;

		return 0;

	}

	//Calculate the needed space
	// max weight = 300kg/palett
	public double calculateSpace() {

		if (testSumP1 != 0) {
			palletSpaceP1 = 0.0;
		}
		
		
		
		
		
		return 0;
	}

}
