package com.github.springular.server.component.user;

public enum Nationality {

	POLAND("PL"),
	GERMANY("DE"),
	NORWAY("NO");

	private String code;

	Nationality(String code) {
		this.code = code;
	}
	
	public String code() {
		return code;
	}
	
}
