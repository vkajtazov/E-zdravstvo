package mk.ukim.finki.ezdravstvo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.sun.xml.bind.v2.schemagen.xmlschema.Appinfo;

import mk.ukim.finki.ezdravstvo.model.AppointmentBooking;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;

public interface AppointmentBookingRepository extends
		JpaSpecificationRepository<AppointmentBooking> {

	List<AppointmentBooking> findByDate(Date date);

	@Query(value = "SELECT ab FROM AppointmentBooking ab WHERE ab.date=?1 AND ab.timeSlot=?2 AND ab.doctor=?3 AND ab.status.id=1")
	AppointmentBooking findByDateAndTimeSlotAndDoctor(Date date,
			TimeSlots timeSlot, Doctor doctor);

	@Query(value = "SELECT ab FROM AppointmentBooking ab WHERE ab.doctor=?1 AND ab.status.id=1")
	List<AppointmentBooking> findByDoctor(Doctor doctor);

	@Query(value = "SELECT ab FROM AppointmentBooking ab WHERE ab.patient=?1 AND ab.status.id=1")
	List<AppointmentBooking> findByPatient(Patient patient);

	@Query(value = "SELECT ab FROM AppointmentBooking ab WHERE ab.referrer=?1 AND ab.status.id=1")
	List<AppointmentBooking> findByReferrer(Doctor referrer);
}
