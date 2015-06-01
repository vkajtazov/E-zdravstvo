package mk.ukim.finki.ezdravstvo.repository;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.ezdravstvo.model.AppointmentBooking;

public interface AppointmentBookingRepository extends
		JpaSpecificationRepository<AppointmentBooking> {
	
	List<AppointmentBooking> findByDate(Date date);
}
