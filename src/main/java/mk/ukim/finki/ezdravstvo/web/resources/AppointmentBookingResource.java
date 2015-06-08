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
			@RequestParam(value = "doctor_id", required = false) Long doctorId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		String tmp = formatter.format(byDate);
		System.out.println(tmp);
		Date date = null;
		try {
			date = formatter.parse(tmp);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		java.sql.Date d = new java.sql.Date(date.getTime() + 1);

		if (doctorId != null) {
			Doctor d1 = doctorService.findOne(doctorId);
			return service.findFreeSlots(d, d1);
		}

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

	@RequestMapping(value = "/byDoctor", method = RequestMethod.GET, produces = "application/json")
	public List<AppointmentBooking> getByDoctor(HttpServletRequest request,
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

	@RequestMapping(value = "/byPatient", method = RequestMethod.GET, produces = "application/json")
	public List<AppointmentBooking> getByPatient(HttpServletRequest request,
			HttpServletResponse response) {
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

	@RequestMapping(value = "/cancel", method = RequestMethod.POST, produces = "application/json")
	public boolean cancel(@RequestParam("id") Long id) {
		return service.cancelAppointment(id);
	}

	@RequestMapping(value = "/bookFromDoctor", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public boolean bookAppointmentFromDoctor(@RequestParam("date") Date date,
			@RequestParam("time_id") Long timeId,
			@RequestParam("patient_id") Long patientId,
			@RequestParam("doctor_id") Long doctorId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		TimeSlots timeSlot = timeSlotsRepository.findOne(timeId);
		if (timeSlot == null)
			return false;

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();

		if (principal instanceof UserDetails) {

			UserDetails userDetails = (UserDetails) principal;
			Doctor referrer = doctorService.findByUsername(userDetails
					.getUsername());
			Doctor doctor = doctorService.findOne(doctorId);
			Patient patient = patientService.findOne(patientId);
			return service.bookAppointmentFromDoctor(date, timeSlot, doctor,
					patient, referrer);
		}
		return false;

	}

	@RequestMapping(value = "/byReferrer", method = RequestMethod.GET, produces = "application/json")
	public List<AppointmentBooking> getByReferrer(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			Doctor referrer = doctorService.findByUsername(userDetails
					.getUsername());
			return service.findByReferrer(referrer);
		}
		return null;
	}
}
