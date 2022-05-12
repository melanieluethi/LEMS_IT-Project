package ch.fhnw.lems.controller.messages;

public class MessageChangeLanguage {
	private Long userId;
	private String language;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}