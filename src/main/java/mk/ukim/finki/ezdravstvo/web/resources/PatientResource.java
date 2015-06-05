package mk.ukim.finki.ezdravstvo.web.resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.User;
import mk.ukim.finki.ezdravstvo.security.UserTransfer;
import mk.ukim.finki.ezdravstvo.service.DoctorService;
import mk.ukim.finki.ezdravstvo.service.PatientService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/patients")
public class PatientResource extends CrudResource<Patient, PatientService> {

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Override
	public PatientService getService() {
		return patientService;
	}

	@RequestMapping(value = "/byDoctor", method = RequestMethod.GET, produces = "application/json")
	private List<Patient> getByDoctor(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String
				&& ((String) principal).equals("anonymousUser")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		if (principal instanceof UserDetails) {

			UserDetails userDetails = (UserDetails) principal;
			Doctor doctor = doctorService.findByUsername(userDetails.getUsername());
			return patientService.findByDoctor(doctor);
		}
		return null;
	}

}
