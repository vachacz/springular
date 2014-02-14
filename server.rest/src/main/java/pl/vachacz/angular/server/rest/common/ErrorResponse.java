package pl.vachacz.angular.server.rest.common;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

	private List<String> messages;

	public ErrorResponse() {
		this(new ArrayList<String>());
	}
	
	public ErrorResponse(List<String> messages) {
		this.setMessages(messages);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public void addMessage(String message) {
		messages.add(message);
	}

}
