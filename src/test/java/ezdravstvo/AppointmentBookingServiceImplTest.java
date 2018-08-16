package ezdravstvo;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.Assert;
import mk.ukim.finki.ezdravstvo.model.AppointmentBooking;
import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Hospital;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;
import mk.ukim.finki.ezdravstvo.model.User;
import mk.ukim.finki.ezdravstvo.model.User.Gender;
import mk.ukim.finki.ezdravstvo.model.User.Role;
import mk.ukim.finki.ezdravstvo.repository.AppointmentBookingRepository;
import mk.ukim.finki.ezdravstvo.service.impl.AppointmentBookingServiceImpl;
import static org.mockito.Mockito.*;


public class AppointmentBookingServiceImplTest {
	
	@Test
	public void  doesExist() {
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.exists((long)1)).thenReturn(true);
		assertEquals(service.exists((long)1), true);
		
	}
	@Test
	public void  cancelAppointment() {
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.cancelAppointment((long)3)).thenReturn(true);
		assertEquals(service.cancelAppointment((long)3), true);
		
	}

	
	@Test
	public void  bookAppointmentDoctor() {
		Doctor doctor = new Doctor();
		doctor.setFistName("Petko");
		doctor.setLastName("Stojanov");
		doctor.setId((long)3);
		Patient pacient = new Patient();
		pacient.setFistName("Ivana");
		pacient.setLastName("Kajtazova");
		pacient.setCity("Gevgelija");
		pacient.setGender(Gender.F);
		pacient.setId((long)1);
		pacient.setPrimaryDoctor(doctor);
		pacient.setRole(Role.ROLE_PATIENT);
		pacient.setUsername("kajtazovai");
		TimeSlots timeslots= new TimeSlots();
		timeslots.setId((long)1);
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.bookAppointmentFromDoctor(Date.valueOf("2018-03-21"), timeslots, doctor, pacient, doctor)).thenReturn(true);	
		assertEquals(service.bookAppointmentFromDoctor(Date.valueOf("2018-03-21"), timeslots, doctor, pacient, doctor), true);
		
	}
	@Test
	public void  findallAppointments () {
		Doctor doctor = new Doctor();
		doctor.setFistName("Petko");
		doctor.setLastName("Stojanov");
		doctor.setId((long)3);
		Patient patient = new Patient();
		patient.setFistName("Ivana");
		patient.setLastName("Kajtazova");
		patient.setId((long)2);
		Diagnose diagnose = new Diagnose();
		diagnose.setDiagnosis("Grip");
		diagnose.setId((long)1);
		diagnose.setDoctor(doctor);
		diagnose.setPatient(patient);
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		List<AppointmentBooking> list = new ArrayList<AppointmentBooking>();
		Doctor doctor1 = new Doctor();
		doctor.setFistName("Petko");
		doctor.setLastName("Stojanov");
		doctor.setId((long)3);
		Patient patient1 = new Patient();
		patient.setFistName("Trajce");
		patient.setLastName("Jovanov");
		patient.setId((long)5);
		Diagnose diagnose1 = new Diagnose();
		diagnose.setDiagnosis("Krven pritisok");
		diagnose.setId((long)3);
		diagnose.setDoctor(doctor);
		diagnose.setPatient(patient);
		
		
		AppointmentBooking booking1 = new AppointmentBooking();
		booking1.setDate(Date.valueOf("2018-03-15"));
		booking1.setDateBooked(Date.valueOf("2018-03-16"));
		booking1.setDiagnose(diagnose);
		AppointmentBooking booking = new AppointmentBooking();
		booking.setDate(Date.valueOf("2018-05-01"));
		booking.setDateBooked(Date.valueOf("2018-05-04"));
		booking.setDiagnose(diagnose1);
		
		list.add(booking1);
		list.add(booking);
		
		Mockito.when(service.findAll()).thenReturn(list);
		assertEquals(service.findAll(),list);
		
	}

	
}
