package mk.ukim.finki.ezdravstvo.web.resources;

import mk.ukim.finki.ezdravstvo.model.Prescription;
import mk.ukim.finki.ezdravstvo.service.PrescriptionService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/prescriptions")
public class PrescriptionResource extends
		CrudResource<Prescription, PrescriptionService> {

	@Autowired
	private PrescriptionService service;

	@Override
	public PrescriptionService getService() {
		return service;
	}

}
