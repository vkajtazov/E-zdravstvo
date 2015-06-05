package mk.ukim.finki.ezdravstvo.service.impl;

import mk.ukim.finki.ezdravstvo.model.Prescription;
import mk.ukim.finki.ezdravstvo.repository.PrescriptionRepository;
import mk.ukim.finki.ezdravstvo.service.PrescriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionServiceImpl extends
		BaseEntityCrudServiceImpl<Prescription, PrescriptionRepository>
		implements PrescriptionService {

	@Autowired
	private PrescriptionRepository repository;

	@Override
	protected PrescriptionRepository getRepository() {
		return repository;
	}

}
