package mk.ukim.finki.ezdravstvo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.repository.DoctorRepository;
import mk.ukim.finki.ezdravstvo.service.DoctorService;

@Service
public class DoctorServiceImpl extends BaseEntityCrudServiceImpl<Doctor, DoctorRepository> implements DoctorService{

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	protected DoctorRepository getRepository() {
		return doctorRepository;
	}

	@Override
	public Doctor findByUsername(String username) {
		return doctorRepository.findByUsername(username);
	}

}
