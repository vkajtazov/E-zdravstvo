package mk.ukim.finki.ezdravstvo.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.ukim.finki.ezdravstvo.model.Specialization;
import mk.ukim.finki.ezdravstvo.service.SpecializationService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

@RestController
@RequestMapping("/rest/specializations")
public class SpecializationResource extends CrudResource<Specialization, SpecializationService>{

	@Autowired
	private SpecializationService service;
	
	@Override
	public SpecializationService getService() {
		return service;
	}

}
