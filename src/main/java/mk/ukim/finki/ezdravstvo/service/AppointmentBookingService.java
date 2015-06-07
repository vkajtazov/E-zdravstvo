package mk.ukim.finki.ezdravstvo.service;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.ezdravstvo.model.AppointmentBooking;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;

public interface AppointmentBookingService extends
		BaseEntityCrudService<AppointmentBooking> {

	List<TimeSlots> findFreeSlots(Date date, Doctor doctor);

	boolean bookAppointment(Date date, TimeSlots timeSlot, Doctor doctor,
			Patient patient);

	List<AppointmentBooking> findByDoctor(Doctor doctor);

	List<AppointmentBooking> findByPatient(Patient patient);

}
