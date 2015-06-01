package mk.ukim.finki.ezdravstvo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "prescriptions")
public class Prescription extends BaseEntity {

	@ManyToOne
	private Diagnose diagnose;

	@OneToOne
	private Medication medication;

	@DateTimeFormat(iso = ISO.DATE)
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

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

}
