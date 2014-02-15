package com.github.springular.server.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 3967010694315999567L;
	
	private List<String> messages = new ArrayList<String>();
	
	public static class Builder {
		
		private BusinessException exception;

		public Builder() {
			exception = new BusinessException();
		}
		
		public Builder addMessage(String message) {
			exception.messages.add(message);
			return this;
		}
		
		public boolean hasMessages() {
			return !exception.messages.isEmpty();
		}

		public BusinessException excetpion() {
			throw exception;
		}
	}
	
	public static Builder build() {
		return new Builder();
	}

	public List<String> getMessages() {
		return messages;
	}
	
}
