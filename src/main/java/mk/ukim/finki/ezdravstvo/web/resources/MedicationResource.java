package mk.ukim.finki.ezdravstvo.web.resources;

import mk.ukim.finki.ezdravstvo.model.Medication;
import mk.ukim.finki.ezdravstvo.service.MedicationService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/medications")
public class MedicationResource extends
		CrudResource<Medication, MedicationService> {

	@Autowired
	private MedicationService service;

	@Override
	public MedicationService getService() {
		return service;
	}
}
