package com.github.springular.server.component.user;

public enum Nationality {

	POLAD("PL"),
	GERMAN("DE"),
	NORWAY("NO");

	private String code;

	Nationality(String code) {
		this.code = code;
	}
	
	public String code() {
		return code;
	}
	
}
