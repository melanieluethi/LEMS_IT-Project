package ch.fhnw.lems.controller.messages;

// lum
public class MessageResultTransportCost extends MessageResultStandard {
	private Double transportCostStandard;
	private Boolean deliveryAvailable;
	private Double priceForDelivery;
	private Double transportCostExpress;
	private Boolean deliveryExpressAvailable;

	public Double getTransportCostStandard() {
		return transportCostStandard;
	}

	public void setTransportCostStandard(Double transportCostStandard) {
		this.transportCostStandard = transportCostStandard;
	}

	public Boolean getDeliveryAvailable() {
		return deliveryAvailable;
	}

	public void setDeliveryAvailable(Boolean deliveryAvailable) {
		this.deliveryAvailable = deliveryAvailable;
	}

	public Double getPriceForDelivery() {
		return priceForDelivery;
	}

	public void setPriceForDelivery(Double priceForDelivery) {
		this.priceForDelivery = priceForDelivery;
	}

	public Double getTransportCostExpress() {
		return transportCostExpress;
	}

	public void setTransportCostExpress(Double transportCostExpress) {
		this.transportCostExpress = transportCostExpress;
	}

	public Boolean getDeliveryExpressAvailable() {
		return deliveryExpressAvailable;
	}

	public void setDeliveryExpressAvailable(Boolean deliveryExpressAvailable) {
		this.deliveryExpressAvailable = deliveryExpressAvailable;
	}
}