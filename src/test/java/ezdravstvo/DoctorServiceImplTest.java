package ezdravstvo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.mockito.Mockito;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.service.impl.DiagnoseServiceImpl;
import mk.ukim.finki.ezdravstvo.service.impl.DoctorServiceImpl;

public class DoctorServiceImplTest {

	@Test
	public void findByDoctorUsername() {
		Doctor doctor = new Doctor();
		doctor.setUsername("petkop");
		
		DoctorServiceImpl service = mock(DoctorServiceImpl.class);
		Mockito.when(service.findByUsername("petkop")).thenReturn(doctor);
		assertEquals(service.findByUsername("petkop"),doctor);
	}
	@Test
	public void findByDoctorUsernameEmpty() {
		Doctor doctor = new Doctor();
		
		
		DoctorServiceImpl service = mock(DoctorServiceImpl.class);
		Mockito.when(service.findByUsername("")).thenReturn(doctor);
		assertEquals(service.findByUsername(""),doctor);
	}

}
