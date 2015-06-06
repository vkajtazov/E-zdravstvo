package mk.ukim.finki.ezdravstvo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.ezdravstvo.model.ChangeRequest;
import mk.ukim.finki.ezdravstvo.repository.ChangeRequestRepository;
import mk.ukim.finki.ezdravstvo.service.ChangeRequestService;

@Service
public class ChangeRequestServiceImpl extends
		BaseEntityCrudServiceImpl<ChangeRequest, ChangeRequestRepository>
		implements ChangeRequestService {

	@Autowired
	private ChangeRequestRepository repository;
	
	@Override
	protected ChangeRequestRepository getRepository() {
		return repository;
	}

}
