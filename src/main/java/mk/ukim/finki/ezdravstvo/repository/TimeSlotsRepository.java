package mk.ukim.finki.ezdravstvo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;

public interface TimeSlotsRepository extends
		JpaSpecificationRepository<TimeSlots> {

	@Query(value = "SELECT t FROM TimeSlots t WHERE t.id NOT IN "
			+ "(SELECT ab.id FROM AppointmentBooking ab WHERE ab.date = ?1 AND ab.doctor = ?2 and ab.status.id=1)")
	List<TimeSlots> findFreeSlots(Date date, Doctor doctor);
}
