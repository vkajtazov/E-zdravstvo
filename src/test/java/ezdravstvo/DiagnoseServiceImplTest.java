package ezdravstvo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;
import mk.ukim.finki.ezdravstvo.service.impl.AppointmentBookingServiceImpl;
import mk.ukim.finki.ezdravstvo.service.impl.DiagnoseServiceImpl;

public class DiagnoseServiceImplTest {
	//Find all diagnoses by patient
	@Test
	public void findPacientDiagnoses() {
		Patient patient = new Patient();
		patient.setFistName("Filip");
		patient.setLastName("Petkov");
		patient.setId((long)3);
		Diagnose diagnoza = new Diagnose();
		diagnoza.setDiagnosis("bronhit");
		diagnoza.setId((long)1);
		diagnoza.setCreatedAt(Date.valueOf("2018-05-10"));
		Diagnose diagnoza1 = new Diagnose();
		diagnoza1.setDiagnosis("kardio zaboluvanje");
		diagnoza1.setId((long)2);
		diagnoza1.setCreatedAt(Date.valueOf("2017-02-18"));
		List<Diagnose> list = new ArrayList<Diagnose>();
		List<Diagnose> result = new ArrayList<Diagnose>();
		result.add(diagnoza);
		result.add(diagnoza1);
		list.add(diagnoza);
		list.add(diagnoza1);
		DiagnoseServiceImpl service = mock(DiagnoseServiceImpl.class);
		Mockito.when(service.findByPatient(patient)).thenReturn(list);
		assertEquals(service.findByPatient(patient), result);
		
	}
	//Find diagnoses for patient where list is empty
	@Test
	public void findByPatientEmpty() {
		Patient patient = new  Patient();
		List<Diagnose> result = new ArrayList<Diagnose>();
		
		List<Diagnose> list = new ArrayList<Diagnose>();
		DiagnoseServiceImpl service = mock(DiagnoseServiceImpl.class);
		Mockito.when(service.findByPatient(patient)).thenReturn(list);
		assertEquals(service.findByPatient(patient), result);
		
		
		
	}

}
