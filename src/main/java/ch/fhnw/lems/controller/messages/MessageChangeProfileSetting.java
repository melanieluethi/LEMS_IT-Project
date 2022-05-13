package ch.fhnw.lems.controller.messages;

//LUM
public class MessageChangeProfileSetting extends MessageUser {
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}