package mk.ukim.finki.ezdravstvo.service.impl;

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

}
