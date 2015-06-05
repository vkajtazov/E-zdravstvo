package mk.ukim.finki.ezdravstvo.security;

import mk.ukim.finki.ezdravstvo.model.User;

public class TokenTransfer {
	private final String token;

	private User user;

	public TokenTransfer(String token, User user) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return this.token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
