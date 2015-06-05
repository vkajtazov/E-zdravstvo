package mk.ukim.finki.ezdravstvo.web.resources;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.ezdravstvo.model.AppointmentBooking;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;
import mk.ukim.finki.ezdravstvo.repository.TimeSlotsRepository;
import mk.ukim.finki.ezdravstvo.service.AppointmentBookingService;
import mk.ukim.finki.ezdravstvo.service.DoctorService;
import mk.ukim.finki.ezdravstvo.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

	@Autowired
	private TimeSlotsRepository timeSlotsRepository;

	@Autowired
	private DoctorService doctorService;

	@RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<TimeSlots> searchSlots(@RequestParam("byDate") Date byDate,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

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

			System.out.println(byDate);

			String tmp = formatter.format(byDate);
			System.out.println(tmp);
			Date date = null;
			try {
				date = formatter.parse(tmp);
				System.out.println(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			java.sql.Date d = new java.sql.Date(date.getTime());

			return service.findFreeSlots(d, patient.getPrimaryDoctor());

		}
		return null;
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public boolean bookAppointment(@RequestParam("date") Date date,
			@RequestParam("time_id") Long timeId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		TimeSlots timeSlot = timeSlotsRepository.findOne(timeId);
		if (timeSlot == null)
			return false;

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

			return service.bookAppointment(date, timeSlot,
					patient.getPrimaryDoctor(), patient);
		}
		return false;

	}

	@Secured({"ROLE_DOCTOR"})
	@RequestMapping(value = "/byDoctor", method = RequestMethod.GET, produces = "application/json")
	private List<AppointmentBooking> getByDoctor(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			Doctor doctor = doctorService.findByUsername(userDetails
					.getUsername());
			return service.findByDoctor(doctor);
		}
		return null;
	}

}
