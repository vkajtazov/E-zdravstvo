package mk.ukim.finki.ezdravstvo.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(name = "id")
public class Patient extends User {

	public Patient() {
		this.setRole(User.Role.ROLE_PATIENT);
	}

	@DateTimeFormat(iso = ISO.DATE)
	private Date birthDate;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String umcn;

	private String address;

	private String city;

	private String country;

	private String postCode;

	@ManyToOne(cascade = CascadeType.REMOVE)
	private Doctor primaryDoctor;

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getUmcn() {
		return umcn;
	}

	public void setUmcn(String umcn) {
		this.umcn = umcn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Doctor getPrimaryDoctor() {
		return primaryDoctor;
	}

	public void setPrimaryDoctor(Doctor primaryDoctor) {
		this.primaryDoctor = primaryDoctor;
	}

}
