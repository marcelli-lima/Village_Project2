package com.example.projeto.model.transport;

public class JwtDTO {


	private String type;
	private String token;
	private long expiration;

	public JwtDTO(String type, String token, long expiration) {
		this.type = type;
		this.token = token;
		this.expiration = expiration;
	}
	
	public String getFullToken() {
		return getType() + " " + getToken();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}
}
