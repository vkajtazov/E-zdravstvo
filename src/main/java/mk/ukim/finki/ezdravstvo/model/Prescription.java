package mk.ukim.finki.ezdravstvo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescription extends BaseEntity {

	@ManyToOne
	private Diagnose diagnose;
	private Date createdAt;
	public Diagnose getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(Diagnose diagnose) {
		this.diagnose = diagnose;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
