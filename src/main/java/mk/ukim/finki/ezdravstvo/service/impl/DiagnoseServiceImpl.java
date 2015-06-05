package mk.ukim.finki.ezdravstvo.service.impl;

import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.repository.DiagnoseRepository;
import mk.ukim.finki.ezdravstvo.service.DiagnoseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnoseServiceImpl extends
		BaseEntityCrudServiceImpl<Diagnose, DiagnoseRepository> implements
		DiagnoseService {

	@Autowired
	private DiagnoseRepository repository;

	@Override
	protected DiagnoseRepository getRepository() {
		return repository;
	}

}
