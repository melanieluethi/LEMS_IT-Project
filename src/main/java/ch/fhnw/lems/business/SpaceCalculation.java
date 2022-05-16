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

	// Grössen Multiplikator --> X Stück pro Palettenplatz
	private double spaceMultiplicatorP1 = 0.04;
	private double spaceMultiplicatorP2 = 0.1;
	private double spaceMultiplicatorP3 = 0.0666666;
	private double spaceMultiplicatorP4 = 0.01;
	
	// Grösse von Palettenplatz
	private double spaceP1 = 1.2;
	private double spaceP2 = 2.0;
	private double spaceP3 = 2.5;
	private double spaceP4 = 0.8;
	

	private double totalHeightP1;
	private double totalHeightP2;
	private double totalHeightP3;
	private double totalHeightP4;

	private double restHeightP1;
	private double restHeightP2;
	private double restHeightP3;
	private double restHeightP4;

	private int palletSpaceP1;
	private int palletSpaceP2;
	private int palletSpaceP3;
	private int palletSpaceP4;
	
	private double shippingSpaceP1;
	private double shippingSpaceP2;
	private double shippingSpaceP3;
	private double shippingSpaceP4;
	private double shippingSpaceTotal;

	private int weightP1;
	private int weightP2;
	private int weightP3;
	private int weightP4;

	private int totalWeight;

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

	private double calculateRestSpace() {
		if (testSumP1 != 0) {
			totalHeightP1 = testSumP1 * spaceMultiplicatorP1;
			palletSpaceP1 = (int) totalHeightP1;
			totalHeightP1 = totalHeightP1 - palletSpaceP1;
		}

		if (testSumP2 != 0) {
			totalHeightP2 = testSumP2 * spaceMultiplicatorP2;
			palletSpaceP2 = (int) totalHeightP2;
			totalHeightP2 = totalHeightP2 - palletSpaceP2;
		}

		if (testSumP3 != 0) {
			totalHeightP3 = testSumP3 * spaceMultiplicatorP3; // 123.456
			palletSpaceP3 = (int) totalHeightP3; // 123 & 123.456
			totalHeightP3 = totalHeightP3 - palletSpaceP3; // 123.456 - 123 = 0.456
		}

		if (testSumP4 != 0) {
			totalHeightP4 = testSumP4 * spaceMultiplicatorP4; // 123.456
			palletSpaceP4 = (int) totalHeightP4; // 123 & 123.456
			totalHeightP4 = totalHeightP4 - palletSpaceP4; // 123.456 - 123 = 0.456
		}

		return 0.0;
	}

	private double useRestSpace() {
		// start mit grösstem
		if (totalHeightP3 != 0) {
			// start mit nächst kleinerem
			if (totalHeightP2 != 0) {
				for (int i; totalHeightP3 <= 1 || totalHeightP2 == 0;) {
					totalHeightP3 = totalHeightP3 + spaceMultiplicatorP2;
					totalHeightP2 = totalHeightP2 - spaceMultiplicatorP2;
				}
			}
			if (totalHeightP1 != 0) {
				for (int i; totalHeightP3 <= 1 || totalHeightP1 == 0;) {
					totalHeightP3 = totalHeightP3 + spaceMultiplicatorP1;
					totalHeightP1 = totalHeightP1 - spaceMultiplicatorP1;
				}
			}
			if (totalHeightP4 != 0) {
				for (int i; totalHeightP3 <= 1 || totalHeightP4 == 0;) {
					totalHeightP3 = totalHeightP3 + spaceMultiplicatorP4;
					totalHeightP4 = totalHeightP4 - spaceMultiplicatorP4;
				}
			}
		}

		// 2t grösstes
		if (totalHeightP2 != 0) {
			// start mit nächst kleinerem
			if (totalHeightP1 != 0) {
				for (int i; totalHeightP2 <= 1 || totalHeightP1 == 0;) {
					totalHeightP2 = totalHeightP2 + spaceMultiplicatorP1;
					totalHeightP1 = totalHeightP1 - spaceMultiplicatorP1;
				}
			}
			if (totalHeightP4 != 0) {
				for (int i; totalHeightP2 <= 1 || totalHeightP4 == 0;) {
					totalHeightP2 = totalHeightP2 + spaceMultiplicatorP4;
					totalHeightP4 = totalHeightP4 - spaceMultiplicatorP4;
				}
			}
		}

		// 3t grösstes
		if (totalHeightP1 != 0) {
			// start mit nächst kleinerem
			if (totalHeightP4 != 0) {
				for (int i; totalHeightP1 <= 1 || totalHeightP4 == 0;) {
					totalHeightP1 = totalHeightP1 + spaceMultiplicatorP4;
					totalHeightP4 = totalHeightP4 - spaceMultiplicatorP4;
				}
			}
		}

		return 0.0;
	}

	// Calculate the needed space
	// max weight = 300kg/palett
	public double calculateSpace() {
	
		// Volle Höhen * Platzbedaf Palettenplatz
		shippingSpaceP1 = palletSpaceP1 * spaceP1;
		shippingSpaceP2 = palletSpaceP2 * spaceP2;
		shippingSpaceP3 = palletSpaceP3 * spaceP3;
		shippingSpaceP4 = palletSpaceP4 * spaceP4;
		
		// Hinzufügen voller Palettenplatz pro angefangene höhe
		if(totalHeightP1 != 0) {
			shippingSpaceP1 += spaceP1;
			totalHeightP1 = 0;
		}
		
		if(totalHeightP2 != 0) {
			shippingSpaceP2 += spaceP2;
			totalHeightP2 = 0;
		}
		
		if(totalHeightP3 != 0) {
			shippingSpaceP3 += spaceP3;
			totalHeightP3 = 0;
		}
		
		if(totalHeightP4 != 0) {
			shippingSpaceP4 += spaceP4;
			totalHeightP4 = 0;
		}

		// Kombinieren aller Palettenplätze
		shippingSpaceTotal = shippingSpaceP1 + shippingSpaceP2 + shippingSpaceP3 + shippingSpaceP4;
		
		// Aufrunden auf ganze Paletten
		shippingSpaceTotal = Math.ceil(shippingSpaceTotal);
		
		
		return shippingSpaceTotal;
	}

	// max 300kg pro Palette
	// P1 max Stack:25 Min Space:1.2 weight: 25pc = 500kg --> 20kg
	// P2 max Stack:10 Min Space:2 weight: 10pc = 600kg --> 60kg
	// P3 max Stack:15 Min Space:2.5 weight 15pc = 870kg --> 58kg
	// P4 max Stack:100 Min Space:0.8 weight: 100pc = 200kg --> 2kg

	
	public double totalPalletts() {
		calculateRestSpace();
		useRestSpace();
		double palletts = calculateSpace();
		
		return palletts;
	}
	
	
	
	
}
