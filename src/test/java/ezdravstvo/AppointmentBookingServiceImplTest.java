package ezdravstvo;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import mk.ukim.finki.ezdravstvo.model.AppointmentBooking;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Hospital;
import mk.ukim.finki.ezdravstvo.model.User;
import mk.ukim.finki.ezdravstvo.model.User.Gender;
import mk.ukim.finki.ezdravstvo.repository.AppointmentBookingRepository;
import mk.ukim.finki.ezdravstvo.service.impl.AppointmentBookingServiceImpl;
import static org.mockito.Mockito.*;


public class AppointmentBookingServiceImplTest {
	@Autowired
	public AppointmentBookingServiceImpl service;
	
	@Mock
	public AppointmentBookingRepository repo;
	
	
	
	
	@Before
	public void setUp()
	{
		Doctor doctor = new Doctor();
		doctor.setFistName("Ivana");
		doctor.setLastName("Kajtazova");
		doctor.setCity("Gevgelija");
		doctor.setEmail("kajtazova@gmail.com");
		doctor.setId((long) 1);
		doctor.setGender(Gender.F);
		doctor.setUsername("kajtazovai");	
		AppointmentBooking booking = new AppointmentBooking();
		booking.setDoctor(doctor);
		booking.setDate(Date.valueOf("2018-5-15"));
		Doctor doctor1 = new Doctor();
		doctor.setFistName("Petko");
		doctor.setLastName("Petkov");
		doctor.setCity("Skopje");
		doctor.setEmail("petkop@gmail.com");
		doctor.setId((long) 2);
		doctor.setGender(Gender.M);
		doctor.setUsername("petkovp");
		AppointmentBooking booking1 = new AppointmentBooking();
		booking.setDoctor(doctor1);
		List<AppointmentBooking> list = new ArrayList<AppointmentBooking>();
		List<AppointmentBooking> list1 = new ArrayList<AppointmentBooking>();
		list1.add(booking);
		list.add(booking1);
		Mockito.when(service.findByDoctor(doctor)).thenReturn(list);
		Mockito.when(service.findAll()).thenReturn(list);
		Mockito.when(service.findOne((long)1)).thenReturn(booking);
		Mockito.when(doctor.getLastName()).thenReturn("Kajtazova");
		Mockito.when(doctor.getCity()).thenReturn("Gevgelija");
		Mockito.when(doctor.getGender()).thenReturn(doctor.getGender());
		Mockito.when(doctor.getId()).thenReturn((long)1);
		
		
	}
	
	
	@Test
	public void  findOneDoctor() {
		AppointmentBooking booking = new AppointmentBooking();
		Doctor doctor = new Doctor();
		doctor.setFistName("Ivana");
		booking.setDoctor(doctor);
		
		Doctor doctor1 = new Doctor();
		doctor1.setFistName("Ivana");
		assertEquals(service.findByDoctor(doctor),doctor1);
		
		
	}

}
