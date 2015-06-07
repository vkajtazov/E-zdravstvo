package mk.ukim.finki.ezdravstvo.web.resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.Prescription;
import mk.ukim.finki.ezdravstvo.service.DiagnoseService;
import mk.ukim.finki.ezdravstvo.service.PrescriptionService;
import mk.ukim.finki.ezdravstvo.web.CrudResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/prescriptions")
public class PrescriptionResource extends
		CrudResource<Prescription, PrescriptionService> {

	@Autowired
	private PrescriptionService service;
	
	@Autowired
	private DiagnoseService diagnoseService;

	@Override
	public PrescriptionService getService() {
		return service;
	}

	@RequestMapping(value = "/byDiagnose/{id}", method = RequestMethod.GET, produces = "application/json")
	private List<Prescription> getByDiagnose(
			@PathVariable Long id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Diagnose tmpDiagnose = diagnoseService.findOne(id);
		
		return service.findByDiagnose(tmpDiagnose);
	}
}
