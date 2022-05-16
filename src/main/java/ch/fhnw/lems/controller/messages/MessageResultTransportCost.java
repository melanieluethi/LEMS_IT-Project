package ch.fhnw.lems.controller.messages;

// lum
public class MessageResultTransportCost extends MessageResultStandard {
	private String transportCostStandard;
	private String transportCostExpress;

	public String getTransportCostStandard() {
		return transportCostStandard;
	}

	public void setTransportCostStandard(String transportCostStandard) {
		this.transportCostStandard = transportCostStandard;
	}

	public String getTransportCostExpress() {
		return transportCostExpress;
	}

	public void setTransportCostExpress(String transportCostExpress) {
		this.transportCostExpress = transportCostExpress;
	}
}