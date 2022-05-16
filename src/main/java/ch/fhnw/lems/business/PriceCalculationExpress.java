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

    public double calculateExpressPrice(double distance, double pallett) {    	
    	// Formula developed by HIS --> Goal: Higher Prices than normal Fee Structure + exponential penalty for more palletts
    	expressPrice = distance * priceFactor + Math.pow(pallett, exponentialWeighting) + expressFee;
    	return expressPrice;
    }
    
    public boolean expressOffer(double pallett) {
    	if (pallett <= 12) {
    		return true;
    	}else {
    		return false;
    	}
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