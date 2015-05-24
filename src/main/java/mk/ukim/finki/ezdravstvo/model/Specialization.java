package mk.ukim.finki.ezdravstvo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "specializations")
public class Specialization extends BaseEntity{

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
