package mk.ukim.finki.ezdravstvo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.ezdravstvo.model.Specialization;
import mk.ukim.finki.ezdravstvo.repository.SpecializationRepository;
import mk.ukim.finki.ezdravstvo.service.SpecializationService;

@Service
public class SpecializationServiceImpl extends BaseEntityCrudServiceImpl<Specialization, SpecializationRepository> implements SpecializationService{

	@Autowired
	private SpecializationRepository specializationRepository;
	
	@Override
	protected SpecializationRepository getRepository() {
		return specializationRepository;
	}

}
