package mk.ukim.finki.ezdravstvo.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "ezdravstvo_users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

	public static enum Role {
		ROLE_PATIENT, ROLE_DOCTOR, ROLE_ADMIN
	}

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String username;

	private String email;

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "user_password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_role", length = 20, nullable = false)
	private Role role;

	public String getFirstName() {
		return firstName;
	}

	public void setFistName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
