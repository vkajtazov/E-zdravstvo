package ezdravstvo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.model.Medication;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.Prescription;
import mk.ukim.finki.ezdravstvo.service.impl.PatientServiceImpl;
import mk.ukim.finki.ezdravstvo.service.impl.PrescriptionServiceImpl;

public class PrescriptionServiceImplTest {

	@Test
	public void findByDiagnose() {
		Diagnose diagnose = new Diagnose();
		diagnose.setDiagnosis("grip");
		diagnose.setId((long)1);
		Medication medication = new Medication();
		medication.setId((long)2);
		medication.setName("Royal Medica");
		Medication medication1 = new Medication();
		medication1.setId((long)2);
		medication1.setName("Filip II");
		Prescription prescription = new Prescription();
		prescription.setDiagnose(diagnose);
		prescription.setId((long)2);
		prescription.setMedication(medication);
		Prescription prescription1 = new Prescription();
		prescription1.setDiagnose(diagnose);
		prescription1.setId((long)3);
		prescription1.setMedication(medication1);
		List<Prescription> list = new ArrayList<Prescription>();
		list.add(prescription);
		list.add(prescription1);
		
		PrescriptionServiceImpl service = mock(PrescriptionServiceImpl.class);
		Mockito.when(service.findByDiagnose(diagnose)).thenReturn(list);
		assertEquals(service.findByDiagnose(diagnose),list);
	}
	@Test
	public void findByDiagnoseEmptyList() {
		Diagnose diagnose = new Diagnose();
		diagnose.setDiagnosis("grip");
		diagnose.setId((long)1);
	
		List<Prescription> list = new ArrayList<Prescription>();
	
		
		PrescriptionServiceImpl service = mock(PrescriptionServiceImpl.class);
		Mockito.when(service.findByDiagnose(diagnose)).thenReturn(list);
		assertEquals(service.findByDiagnose(diagnose),list);
	}

}
