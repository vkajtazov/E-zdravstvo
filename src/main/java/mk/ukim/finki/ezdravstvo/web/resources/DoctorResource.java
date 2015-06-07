package mk.ukim.finki.ezdravstvo.web.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.service.DoctorService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

@RestController
@RequestMapping("/rest/doctors")
public class DoctorResource extends CrudResource<Doctor, DoctorService> {

	@Autowired
	private DoctorService doctorService;

	@Override
	public DoctorService getService() {
		return doctorService;
	}

	@Override
	public Doctor create(@RequestBody @Valid Doctor entity,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (entity.getId() != null) {
			Doctor tmpDoctor = doctorService.findOne(entity.getId());
			if (tmpDoctor != null) {
				entity.setPassword(tmpDoctor.getPassword());
			}
		}
		return super.create(entity, request, response);
	}

}
