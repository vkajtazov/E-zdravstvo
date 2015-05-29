package mk.ukim.finki.ezdravstvo.web.resources;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;
import mk.ukim.finki.ezdravstvo.service.AppointmentBookingService;
import mk.ukim.finki.ezdravstvo.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/bookings")
public class AppointmentBookingResource {

	@Autowired
	private AppointmentBookingService service;

	@Autowired
	private PatientService patientService;

	@RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<TimeSlots> searchSlots(
			@RequestParam("byDate") @DateTimeFormat(iso = ISO.DATE) String byDate,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String
				&& ((String) principal).equals("anonymousUser")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		if (principal instanceof UserDetails) {

			UserDetails userDetails = (UserDetails) principal;
			Patient patient = patientService.findByUsername(userDetails
					.getUsername());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				 
				java.util.Date date = formatter.parse(byDate);
				Date d = new Date(date.getTime());
				System.out.println(d);
				return service.findFreeSlots(d, patient.getPrimaryDoctor());
		 
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
