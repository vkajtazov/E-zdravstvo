package ezdravstvo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.service.impl.DoctorServiceImpl;
import mk.ukim.finki.ezdravstvo.service.impl.PatientServiceImpl;

public class PatientServiceImplTest {

	@Test
	public void findByUsername() {
		Patient patient = new Patient();
		patient.setUsername("petkop");
		
		PatientServiceImpl service = mock(PatientServiceImpl.class);
		Mockito.when(service.findByUsername("petkop")).thenReturn(patient);
		assertEquals(service.findByUsername("petkop"),patient);
	}
	@Test
	public void findByUsernameEmpty() {
		Patient patient = new Patient();
		patient.setUsername("");
		
		
		PatientServiceImpl service = mock(PatientServiceImpl.class);
		Mockito.when(service.findByUsername("")).thenReturn(patient);
		assertEquals(service.findByUsername(""),patient);
	}
	@Test
	public void findByDoctor() {
		Doctor doctor = new Doctor();
		doctor.setId((long)1);
		doctor.setFistName("Jovan");
		doctor.setLastName("Trajkov");
		Patient patient = new Patient();
		patient.setFistName("Ratko");
		patient.setLastName("Jovanov");
		Patient patient1 = new Patient();
		patient1.setFistName("Stojan");
		patient1.setLastName("Gligorov");
		Patient patient2 = new Patient();
		patient2.setFistName("Trajce");
		patient2.setLastName("Stojanov");
		List<Patient>patients = new ArrayList<Patient>();
		patients.add(patient);
		patients.add(patient1);
		patients.add(patient2);
		
		
		
		PatientServiceImpl service = mock(PatientServiceImpl.class);
		Mockito.when(service.findByDoctor(doctor)).thenReturn(patients);
		assertEquals(service.findByDoctor(doctor),patients);
	}
	@Test
	public void findByDoctorEmptyList() {
		Doctor doctor = new Doctor();
		doctor.setId((long)1);
		doctor.setFistName("Jovan");
		doctor.setLastName("Trajkov");
	
		List<Patient>patients = new ArrayList<Patient>();
	
		PatientServiceImpl service = mock(PatientServiceImpl.class);
		Mockito.when(service.findByDoctor(doctor)).thenReturn(patients);
		assertEquals(service.findByDoctor(doctor),patients);
	}


}
