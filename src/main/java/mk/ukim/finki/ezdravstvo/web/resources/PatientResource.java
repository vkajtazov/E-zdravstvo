package mk.ukim.finki.ezdravstvo.web.resources;

import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.service.PatientService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/patients")
public class PatientResource extends CrudResource<Patient, PatientService> {

	@Autowired
	private PatientService patientService;

	@Override
	public PatientService getService() {
		return patientService;
	}

}
