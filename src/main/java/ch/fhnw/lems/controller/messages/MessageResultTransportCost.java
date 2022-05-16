package ch.fhnw.lems.controller.messages;

// lum
public class MessageResultTransportCost extends MessageResultStandard {
	private Double transportCostStandard;
	private Double transportCostExpress;

	public Double getTransportCostStandard() {
		return transportCostStandard;
	}

	public void setTransportCostStandard(Double transportCostStandard) {
		this.transportCostStandard = transportCostStandard;
	}

	public Double getTransportCostExpress() {
		return transportCostExpress;
	}

	public void setTransportCostExpress(Double transportCostExpress) {
		this.transportCostExpress = transportCostExpress;
	}
}