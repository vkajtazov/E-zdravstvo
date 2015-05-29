package mk.ukim.finki.ezdravstvo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "appointments_booking")
public class AppointmentBooking extends BaseEntity{

	@OneToOne
	private Doctor doctor;
	
	@OneToOne
	private Patient patient;
	
	@OneToOne
	private Doctor referrer;
	
	@DateTimeFormat(iso = ISO.DATE)
	private Date date;
	
	@OneToOne
	private TimeSlots timeSlot;
	
	@OneToOne
	private BookingStatus status;
	
	private java.util.Date dateBooked;
	
	private java.util.Date dateUpdated;
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TimeSlots getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlots timeSlot) {
		this.timeSlot = timeSlot;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public Doctor getReferrer() {
		return referrer;
	}

	public void setReferrer(Doctor referrer) {
		this.referrer = referrer;
	}

	public java.util.Date getDateBooked() {
		return dateBooked;
	}

	public void setDateBooked(java.util.Date dateBooked) {
		this.dateBooked = dateBooked;
	}

	public java.util.Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(java.util.Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
}
