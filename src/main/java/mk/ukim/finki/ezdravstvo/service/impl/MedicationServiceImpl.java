package mk.ukim.finki.ezdravstvo.service.impl;

import mk.ukim.finki.ezdravstvo.model.Medication;
import mk.ukim.finki.ezdravstvo.repository.MedicationRepository;
import mk.ukim.finki.ezdravstvo.service.MedicationService;

import org.springframework.beans.factory.annotation.Autowired;

public class MedicationServiceImpl extends
		BaseEntityCrudServiceImpl<Medication, MedicationRepository> implements
		MedicationService {

	@Autowired
	private MedicationRepository repository;

	@Override
	protected MedicationRepository getRepository() {
		return repository;
	}

}
