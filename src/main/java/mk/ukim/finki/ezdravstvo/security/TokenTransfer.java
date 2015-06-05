package mk.ukim.finki.ezdravstvo.security;

import mk.ukim.finki.ezdravstvo.model.User;

public class TokenTransfer {
	private final String token;

	private User.Role role;

	public TokenTransfer(String token, User.Role role) {
		this.token = token;
		this.role = role;
	}

	public String getToken() {
		return this.token;
	}

	public User.Role getRole() {
		return role;
	}

	public void setRole(User.Role role) {
		this.role = role;
	}

}
