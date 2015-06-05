package mk.ukim.finki.ezdravstvo.security;

import mk.ukim.finki.ezdravstvo.model.User;

public class UserTransfer {

	private final String username;
	private final User user;

	public UserTransfer(String username, User user) {
		this.username = username;
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public User getUser() {
		return user;
	}

}
