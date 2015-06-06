package mk.ukim.finki.ezdravstvo.web.resources;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mk.ukim.finki.ezdravstvo.model.ChangeRequest;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.service.ChangeRequestService;
import mk.ukim.finki.ezdravstvo.service.DoctorService;
import mk.ukim.finki.ezdravstvo.service.PatientService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

@RestController
@RequestMapping("/rest/requests")
public class ChangeRequestResource extends
		CrudResource<ChangeRequest, ChangeRequestService> {

	@Autowired
	private ChangeRequestService service;

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Override
	public ChangeRequestService getService() {
		return service;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@Override
	public ChangeRequest create(@RequestBody @Valid ChangeRequest entity,
			HttpServletRequest request, HttpServletResponse response) {
		if (entity.getStatus() == null) {
			return null;
		}

		ChangeRequest tmpRequest = null;
		if (entity.getId() != null) {
			tmpRequest = service.findOne(entity.getId());
		}
		Date tmpDate = new Date();

		if (tmpRequest == null) {
			entity.setRequestCreated(tmpDate);
			entity.setRequestUpdated(tmpDate);
			entity.setStatus(ChangeRequest.Status.PENDING);
			return super.create(entity, request, response);
		} else if (entity.getStatus() == ChangeRequest.Status.APPROVED) {

			Patient tmpPatient = patientService.findOne(tmpRequest.getPatient()
					.getId());
			Doctor tmpDoctor = doctorService.findOne(tmpRequest.getDoctor()
					.getId());
			tmpPatient.setPrimaryDoctor(tmpDoctor);
			patientService.save(tmpPatient);

		}
		tmpRequest.setStatus(entity.getStatus());
		tmpRequest.setRequestUpdated(tmpDate);
		return super.create(tmpRequest, request, response);
	}
}
