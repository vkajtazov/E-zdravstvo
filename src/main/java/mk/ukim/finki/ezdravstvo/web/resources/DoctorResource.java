package mk.ukim.finki.ezdravstvo.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.service.DoctorService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

@RestController
@RequestMapping("/rest/doctors")
public class DoctorResource extends CrudResource<Doctor, DoctorService>{

	@Autowired
	private DoctorService doctorService;
	
	@Override
	public DoctorService getService() {
		return doctorService;
	}

}
