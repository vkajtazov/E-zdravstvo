package mk.ukim.finki.ezdravstvo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.ezdravstvo.model.AppointmentBooking;
import mk.ukim.finki.ezdravstvo.model.BookingStatus;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.EmailMessage;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;
import mk.ukim.finki.ezdravstvo.repository.AppointmentBookingRepository;
import mk.ukim.finki.ezdravstvo.repository.BookingStatusRepository;
import mk.ukim.finki.ezdravstvo.repository.TimeSlotsRepository;
import mk.ukim.finki.ezdravstvo.service.AppointmentBookingService;
import mk.ukim.finki.ezdravstvo.service.mail.EmailNotificationService;

@Service
public class AppointmentBookingServiceImpl
		extends
		BaseEntityCrudServiceImpl<AppointmentBooking, AppointmentBookingRepository>
		implements AppointmentBookingService {

	@Autowired
	private AppointmentBookingRepository repository;

	@Autowired
	private TimeSlotsRepository timeSlotsRepository;

	@Autowired
	private BookingStatusRepository statusRepository;

	@Autowired
	private EmailNotificationService emailNotificationService;

	@Override
	protected AppointmentBookingRepository getRepository() {
		return repository;
	}

	@Override
	public List<TimeSlots> findFreeSlots(Date date, Doctor doctor) {
		return timeSlotsRepository.findFreeSlots(date, doctor);
	}

	@Override
	public boolean bookAppointment(Date date, TimeSlots timeSlot,
			Doctor doctor, Patient patient) {
		AppointmentBooking tmp = new AppointmentBooking();

		java.sql.Date date1 = formatDate(date);

		EmailMessage message = EmailMessage.create().subject("test email")
				.to(patient.getEmail()).template("test_email.vm")
				.addToModel("firstName", patient.getFirstName())
				.addToModel("lastName", patient.getLastName())
				.addToModel("date", date1.toString())
				.addToModel("timeFrom", timeSlot.getStartTime())
				.addToModel("timeTo", timeSlot.getEndTime());

		tmp.setDate(date1);
		tmp.setDoctor(doctor);
		tmp.setTimeSlot(timeSlot);
		tmp.setStatus(statusRepository.findOne((long) 1));
		Date tmpDate = new Date();
		tmp.setDateBooked(tmpDate);
		tmp.setDateUpdated(tmpDate);
		tmp.setPatient(patient);

		synchronized (this) {
			AppointmentBooking ab = repository.findByDateAndTimeSlotAndDoctor(
					date1, timeSlot, doctor);
			if (ab == null) {
				repository.save(tmp);
				emailNotificationService.sendEmailAsync(message);
				return true;
			}
		}
		return false;
	}

	private java.sql.Date formatDate(Date utilDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String tmp = formatter.format(utilDate);

		Date date = null;
		try {
			date = formatter.parse(tmp);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		java.sql.Date d = new java.sql.Date(date.getTime());
		return d;
	}

	@Override
	public List<AppointmentBooking> findByDoctor(Doctor doctor) {
		return repository.findByDoctor(doctor);
	}

	@Override
	public List<AppointmentBooking> findByPatient(Patient patient) {
		return repository.findByPatient(patient);
	}

	@Override
	public boolean cancelAppointment(Long appointment_id) {
		AppointmentBooking tmpAppointment = repository.findOne(appointment_id);

		if (tmpAppointment == null) {
			return false;
		}

		BookingStatus tmpStatus = statusRepository.findOne((long) 2);
		tmpAppointment.setStatus(tmpStatus);
		tmpAppointment.setDateUpdated(new Date());
		repository.save(tmpAppointment);
		return true;
	}

	@Override
	public boolean bookAppointmentFromDoctor(Date date, TimeSlots timeSlot,
			Doctor doctor, Patient patient, Doctor referrer) {
		AppointmentBooking tmp = new AppointmentBooking();

		java.sql.Date date1 = formatDate(date);

		EmailMessage message = EmailMessage.create().subject("УПАТ")
				.to(patient.getEmail()).template("test_email.vm")
				.addToModel("firstName", patient.getFirstName())
				.addToModel("lastName", patient.getLastName())
				.addToModel("docFName", doctor.getFirstName())
				.addToModel("docLName", doctor.getLastName())
				.addToModel("date", date1.toString())
				.addToModel("timeFrom", timeSlot.getStartTime())
				.addToModel("timeTo", timeSlot.getEndTime());

		tmp.setDate(date1);
		tmp.setDoctor(doctor);
		tmp.setTimeSlot(timeSlot);
		tmp.setStatus(statusRepository.findOne((long) 1));
		Date tmpDate = new Date();
		tmp.setDateBooked(tmpDate);
		tmp.setDateUpdated(tmpDate);
		tmp.setPatient(patient);
		tmp.setReferrer(referrer);

		synchronized (this) {
			AppointmentBooking ab = repository.findByDateAndTimeSlotAndDoctor(
					date1, timeSlot, doctor);
			if (ab == null) {
				repository.save(tmp);
				emailNotificationService.sendEmailAsync(message);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<AppointmentBooking> findByReferrer(Doctor referrer) {
		return repository.findByReferrer(referrer);
	}
}
