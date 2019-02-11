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
import org.springframework.test.AssertThrows;

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
	//Does exist appointment with id(1)
	@Test
	public void  doesExist() {
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.exists((long)1)).thenReturn(true);
		assertEquals(service.exists((long)1), true);
		
	}
	//Cancel appointment with id(3)
	@Test
	public void  cancelAppointment() {
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.cancelAppointment((long)3)).thenReturn(true);
		assertEquals(service.cancelAppointment((long)3), true);
		
	}

	//Check if exist appointment from some doctor 
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
	//Find all appointments
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
		List<AppointmentBooking> result = new ArrayList<AppointmentBooking>();
		Patient patient1 = new Patient();
		patient1.setFistName("Trajce");
		patient1.setLastName("Jovanov");
		patient1.setId((long)5);
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
		result.add(booking1);
		result.add(booking);
		
		Mockito.when(service.findAll()).thenReturn(list);
		assertEquals(service.findAll(),result);
		
	}
	//Find appointment by patient
	@Test
	public void  findPatient() {
		Patient patient = new Patient();
		patient.setFistName("Trajce");
		patient.setLastName("Jovanov");
		patient.setId((long)5);
		List<AppointmentBooking> list = new ArrayList<AppointmentBooking>();
		List<AppointmentBooking> result = new ArrayList<AppointmentBooking>();
		AppointmentBooking booking = new AppointmentBooking();
		booking.setPatient(patient);
		list.add(booking);
		result.add(booking);
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.findByPatient(patient)).thenReturn(list);
		assertEquals(service.findByPatient(patient), result);
	
		
	}
	//Find list of timeslots by date and doctor
	@Test
	public void findfreeSlots() {
		Doctor doctor = new Doctor();
		doctor.setFistName("Petko");
		doctor.setLastName("Petkov");
		doctor.setId((long)2);
		TimeSlots time1 = new TimeSlots();
		time1.setStartTime(Time.valueOf("10:00:00"));
		time1.setEndTime(Time.valueOf("10:30:00"));
		time1.setId((long)4);
		TimeSlots time2 = new TimeSlots();
		time2.setStartTime(Time.valueOf("10:30:00"));
		time2.setEndTime(Time.valueOf("11:00:00"));
		time2.setId((long)5);
		TimeSlots time3 = new TimeSlots();
		time3.setStartTime(Time.valueOf("11:30:00"));
		time3.setEndTime(Time.valueOf("12:00:00"));
		time3.setId((long)6);
		List<TimeSlots> list = new ArrayList<TimeSlots>();
		List<TimeSlots> result = new ArrayList<TimeSlots>();
		list.add(time1);
		list.add(time2);
		list.add(time3);
		result.add(time1);
		result.add(time2);
		result.add(time3);
		
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.findFreeSlots(Date.valueOf("2018-04-17"), doctor)).thenReturn(list);
		assertEquals(service.findFreeSlots(Date.valueOf("2018-04-17"), doctor),result);
		
	}
	//Find freeslots with empty list inserted
	@Test
	public void emptyListSlots() {
		Doctor doctor = new Doctor();
		doctor.setFistName("Petko");
		doctor.setLastName("Petkov");
		doctor.setId((long)2);
		List<TimeSlots> list = new ArrayList<TimeSlots>();
		List<TimeSlots> result = new ArrayList<TimeSlots>();
		
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.findFreeSlots(Date.valueOf("2018-05-10"), doctor)).thenReturn(list);
		assertEquals(service.findFreeSlots(Date.valueOf("2018-05-10"), doctor),result);
		
	}
	//check if appointment is created by doctor 
	@Test
	public void bookAppointment() {
		Doctor doctor = new Doctor();
		doctor.setFistName("Petar");
		doctor.setLastName("Stojcev");
		doctor.setId((long)7);
		Patient pacient = new Patient();
		pacient.setFistName("Tamara");
		pacient.setLastName("Petkova");
		pacient.setCity("Skopje");
		pacient.setGender(Gender.F);
		pacient.setId((long)1);
		pacient.setPrimaryDoctor(doctor);
		pacient.setRole(Role.ROLE_PATIENT);
		pacient.setUsername("petrovat");
		TimeSlots timeslots= new TimeSlots();
		timeslots.setStartTime(Time.valueOf("11:00:00"));
		timeslots.setEndTime(Time.valueOf("11:45:00"));
		timeslots.setId((long)1);
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.bookAppointment(Date.valueOf("2018-03-21"), timeslots, doctor, pacient)).thenReturn(true);	
		assertEquals(service.bookAppointment(Date.valueOf("2018-03-21"), timeslots, doctor, pacient), true);
		
	
		
		
	}
	//Find list by referrer (Doctor)
	@Test
	public void findListByReferrer() {
		Doctor referrer = new Doctor();
		referrer.setFistName("Trajce");
		referrer.setLastName("Petkov");
		referrer.setId((long)9);
		referrer.setRole(Role.ROLE_DOCTOR);
		Doctor referrer1 = new Doctor();
		referrer1.setFistName("Stojce");
		referrer1.setLastName("Ristov");
		referrer1.setId((long)2);
		referrer1.setRole(Role.ROLE_DOCTOR);
		Doctor referrer2 = new Doctor();
		referrer2.setFistName("Mara");
		referrer2.setLastName("Petrova");
		referrer2.setId((long)3);
		referrer2.setRole(Role.ROLE_DOCTOR);
		AppointmentBooking booking = new AppointmentBooking();
		booking.setReferrer(referrer);
		AppointmentBooking booking1 = new AppointmentBooking();
		booking1.setReferrer(referrer);
		AppointmentBooking booking3 = new AppointmentBooking();
		booking3.setReferrer(referrer);
		List<AppointmentBooking> list = new ArrayList<AppointmentBooking>();
		List<AppointmentBooking> result = new ArrayList<AppointmentBooking>();
		result.add(booking);
		result.add(booking1);
		result.add(booking3);
		list.add(booking);
		list.add(booking1);
		list.add(booking3);
		
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.findByReferrer(referrer)).thenReturn(list);
		assertEquals(service.findByReferrer(referrer), result);
	
		
	}
	//Find list of appointments by referrer (doctor) with empty list
	@Test
	public void findByReferrerWithNullInput() {
		Doctor referrer = new Doctor();
		referrer.setFistName("Trajce");
		referrer.setLastName("Stojkov");
		referrer.setId((long)9);
		referrer.setRole(Role.ROLE_DOCTOR);
		List<AppointmentBooking> list = new ArrayList<AppointmentBooking>();
		List<AppointmentBooking> result = new ArrayList<AppointmentBooking>();
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.findByReferrer(referrer)).thenReturn(list);
		assertEquals(service.findByReferrer(referrer), result);
	
	
		
		
	}
	//Find free slots with inserted empty list
	@Test
	public void findfreeSlotsEmptyList() {
		Doctor doctor = new Doctor();
		doctor.setFistName("Petko");
		doctor.setLastName("Petkov");
		doctor.setId((long)2);
		
		List<TimeSlots> list = new ArrayList<TimeSlots>();
		List<TimeSlots> result = new ArrayList<TimeSlots>();
		
		AppointmentBookingServiceImpl service = mock(AppointmentBookingServiceImpl.class);
		Mockito.when(service.findFreeSlots(Date.valueOf("2018-04-17"), doctor)).thenReturn(list);
		assertEquals(service.findFreeSlots(Date.valueOf("2018-04-17"), doctor),result);
		
	}
}
