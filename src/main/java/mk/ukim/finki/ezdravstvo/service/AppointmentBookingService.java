package mk.ukim.finki.ezdravstvo.service;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.ezdravstvo.model.AppointmentBooking;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;

public interface AppointmentBookingService extends BaseEntityCrudService<AppointmentBooking>{

	List<TimeSlots> findFreeSlots(Date date, Doctor doctor);
}
