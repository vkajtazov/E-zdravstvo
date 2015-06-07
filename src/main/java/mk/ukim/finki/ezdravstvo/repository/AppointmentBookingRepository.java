package mk.ukim.finki.ezdravstvo.repository;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.ezdravstvo.model.AppointmentBooking;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;

public interface AppointmentBookingRepository extends
		JpaSpecificationRepository<AppointmentBooking> {

	List<AppointmentBooking> findByDate(Date date);

	AppointmentBooking findByDateAndTimeSlotAndDoctor(Date date,
			TimeSlots timeSlot, Doctor doctor);
	
	List<AppointmentBooking> findByDoctor(Doctor doctor);
	
	List<AppointmentBooking> findByPatient(Patient patient);
}
