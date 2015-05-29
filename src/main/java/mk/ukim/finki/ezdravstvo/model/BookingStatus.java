package mk.ukim.finki.ezdravstvo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "booking_status")
public class BookingStatus extends BaseEntity{

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
