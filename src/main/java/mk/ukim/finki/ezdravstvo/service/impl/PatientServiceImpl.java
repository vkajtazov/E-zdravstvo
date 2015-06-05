package mk.ukim.finki.ezdravstvo.service.impl;

import java.util.List;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.repository.PatientRepository;
import mk.ukim.finki.ezdravstvo.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl extends
		BaseEntityCrudServiceImpl<Patient, PatientRepository> implements
		PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	protected PatientRepository getRepository() {
		return patientRepository;
	}

	@Override
	public Patient findByUsername(String username) {
		return patientRepository.findByUsername(username);
	}

	@Override
	public List<Patient> findByDoctor(Doctor doctor) {
		return patientRepository.findByPrimaryDoctor(doctor);
	}

}
