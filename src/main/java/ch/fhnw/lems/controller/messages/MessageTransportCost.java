package ch.fhnw.lems.controller.messages;

// lum
public class MessageTransportCost {
	private Long id;
	private String idType; // need to be cardId or orderId

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}
}