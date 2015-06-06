package mk.ukim.finki.ezdravstvo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "change_doctor_request")
public class ChangeRequest extends BaseEntity {

	public static enum Status {
		PENDING, APPROVED, REJECTED
	}

	@OneToOne
	private Patient patient;

	@ManyToOne
	private Doctor doctor;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date requestCreated;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date requestUpdated;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getRequestCreated() {
		return requestCreated;
	}

	public void setRequestCreated(Date requestCreated) {
		this.requestCreated = requestCreated;
	}

	public Date getRequestUpdated() {
		return requestUpdated;
	}

	public void setRequestUpdated(Date requestUpdated) {
		this.requestUpdated = requestUpdated;
	}

}
