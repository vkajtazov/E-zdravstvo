package mk.ukim.finki.ezdravstvo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.ezdravstvo.model.Hospital;
import mk.ukim.finki.ezdravstvo.repository.HospitalRepository;
import mk.ukim.finki.ezdravstvo.service.HospitalService;

@Service
public class HospitalServiceImpl extends
		BaseEntityCrudServiceImpl<Hospital, HospitalRepository> implements
		HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Override
	protected HospitalRepository getRepository() {
		return hospitalRepository;
	}

}
