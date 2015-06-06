package mk.ukim.finki.ezdravstvo.web.resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.service.DiagnoseService;
import mk.ukim.finki.ezdravstvo.service.PatientService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/diagnoses")
public class DiagnoseResource extends CrudResource<Diagnose, DiagnoseService> {

	@Autowired
	private DiagnoseService service;

	@Autowired
	private PatientService patientService;

	@Override
	public DiagnoseService getService() {
		return service;
	}

	@RequestMapping(value = "/byPatient", method = RequestMethod.GET, produces = "application/json")
	private List<Diagnose> getByPatient(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			Patient patient = patientService.findByUsername(userDetails
					.getUsername());
			return service.findByPatient(patient);
		}
		return null;
	}
}
