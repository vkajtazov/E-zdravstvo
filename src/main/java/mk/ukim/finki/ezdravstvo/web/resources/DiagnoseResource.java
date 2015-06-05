package mk.ukim.finki.ezdravstvo.web.resources;

import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.service.DiagnoseService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/diagnoses")
public class DiagnoseResource extends CrudResource<Diagnose, DiagnoseService> {

	@Autowired
	private DiagnoseService service;

	@Override
	public DiagnoseService getService() {
		return service;
	}

}
