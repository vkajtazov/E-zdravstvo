package mk.ukim.finki.ezdravstvo.web.resources;

import mk.ukim.finki.ezdravstvo.model.Hospital;
import mk.ukim.finki.ezdravstvo.service.HospitalService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hospitals")
public class HospitalResource extends CrudResource<Hospital, HospitalService>{

	@Autowired
	private HospitalService hospitalService;
	
	@Override
	public HospitalService getService() {
		return hospitalService;
	}

}
