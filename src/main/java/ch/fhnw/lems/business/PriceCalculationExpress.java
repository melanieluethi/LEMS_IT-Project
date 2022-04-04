package ch.fhnw.lems.business;

public class PriceCalculationExpress {

	
	
	//done by HiS
    //Preiskalulation EXPRESS

    //Formel: x=Distanz, y=Anz.Pal, a=Faktor, b=GewichtungPal, c=Zusatzgebühr
    // Preis = x*a + y^b * a + c

    private double expressPrice;
    private double priceFactor = 1.5;
    private double exponentialWeighting = 2;
    private double expressFee = 80;

    //justForTesting -->pls delete befor handing in
    private double distance;
    private double pallett;



    public double calculateExpressPrice() {
    	
    	//add fancy calculations
    	
    	
    	return 0.0;
    }





    public double getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(double expressPrice) {
        this.expressPrice = expressPrice;
    }

    public double getPriceFactor() {
        return priceFactor;
    }

    public void setPriceFactor(double priceFactor) {
        this.priceFactor = priceFactor;
    }

    public double getExponentialWeighting() {
        return exponentialWeighting;
    }

    public void setExponentialWeighting(double exponentialWeighting) {
        this.exponentialWeighting = exponentialWeighting;
    }

    public double getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(double expressFee) {
        this.expressFee = expressFee;
    }


}
