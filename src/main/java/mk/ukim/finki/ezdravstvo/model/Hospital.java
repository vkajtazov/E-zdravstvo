package mk.ukim.finki.ezdravstvo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="hospitals")
public class Hospital extends BaseEntity{

	private String name;
	
	private String address;
	
	private String city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
