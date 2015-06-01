package mk.ukim.finki.ezdravstvo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.ezdravstvo.model.AppointmentBooking;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;
import mk.ukim.finki.ezdravstvo.repository.AppointmentBookingRepository;
import mk.ukim.finki.ezdravstvo.repository.TimeSlotsRepository;
import mk.ukim.finki.ezdravstvo.service.AppointmentBookingService;

@Service
public class AppointmentBookingServiceImpl extends BaseEntityCrudServiceImpl<AppointmentBooking, AppointmentBookingRepository> implements AppointmentBookingService{

	@Autowired
	private AppointmentBookingRepository repository;
	
	@Autowired
	private TimeSlotsRepository timeSlotsRepository;
	
	@Override
	protected AppointmentBookingRepository getRepository() {
		return repository;
	}

	@Override
	public List<TimeSlots> findFreeSlots(Date date, Doctor doctor) {
		return timeSlotsRepository.findFreeSlots(date, doctor);
	}



}
