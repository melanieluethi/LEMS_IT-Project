package ch.fhnw.lems.service.messages;

//LUM
public class MessageResultStandard {
	private boolean successful;
	private Long id;

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}