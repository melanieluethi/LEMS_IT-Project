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

	private static int WEIGHT_MULTIPLICATOR_P1 = 20;
	private static int weightMultiplicatorP2 = 60;
	private static int weightMultiplicatorP3 = 58;
	private static int weightMultiplicatorP4 = 2;

	// WEIGHT_MULTIPLICATOR_P2

	private double spaceMultiplicatorP1 = 0.0;
	private double spaceMultiplicatorP2 = 0.0;
	private double spaceMultiplicatorP3 = 0.0;
	private double spaceMultiplicatorP4 = 0.0;

	private int weightP1;
	private int weightP2;
	private int weightP3;
	private int weightP4;

	private int totalWeight;

	private double palletSpaceP1;
	private double palletSpaceP2;
	private double palletSpaceP3;
	private double palletSpaceP4;

	// calculate the weight per Product
	public double calculateWight() {

		if (testSumP1 != 0) {
			weightP1 = testSumP1 * WEIGHT_MULTIPLICATOR_P1;
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

		return totalWeight;

	}

	// input Matthias:
	// Start bei breitestem Produkt
	// anschliessend nur noch die höhe relevant

	// Calculate the needed space
	// max weight = 300kg/palett
	public double calculateSpace() {

		if (testSumP2 != 0) {
			int p = 0;
			double h= 0.0;
			
			h = testSumP2/10;
			
			
			palletSpaceP2 = 2 * p;

		}

		// p1/platz1 = 2 paletten
		// ...
		// = gesamt = 7.5 = 8.0

		// asdasda sdasd
		// summ of all pallets must be <= 12

		return 0;
	}

	// max 300kg pro Palette
	// P1 max Stack:25 Min Space:1.2 weight: 25pc = 500kg --> 20kg
	// P2 max Stack:10 Min Space:2 weight: 10pc = 600kg --> 60kg
	// P3 max Stack:15 Min Space:2.5 weight 15pc = 870kg --> 58kg
	// P4 max Stack:100 Min Space:0.8 weight: 100pc = 200kg --> 2kg

}
