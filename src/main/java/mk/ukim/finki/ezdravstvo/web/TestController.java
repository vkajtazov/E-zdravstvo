package mk.ukim.finki.ezdravstvo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.EmailMessage;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.TimeSlots;
import mk.ukim.finki.ezdravstvo.model.User;
import mk.ukim.finki.ezdravstvo.repository.TimeSlotsRepository;
import mk.ukim.finki.ezdravstvo.service.AppointmentBookingService;
import mk.ukim.finki.ezdravstvo.service.DoctorService;
import mk.ukim.finki.ezdravstvo.service.PatientService;
import mk.ukim.finki.ezdravstvo.service.mail.EmailNotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private EmailNotificationService emailNotificationService;

	@Autowired
	private TimeSlotsRepository timeSlotsRepository;

	@Autowired
	private AppointmentBookingService bookingService;

	@Autowired
	private PatientService patientService;

	@RequestMapping(value = "/addDoctor/{doc}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Doctor createDoctor(@PathVariable String doc) {
		Doctor tmp = new Doctor();
		tmp.setFistName("Бла бла доктор");
		tmp.setUsername(doc);
		tmp = (Doctor) tmp;
		tmp.setPassword("qwe");
		return doctorService.save(tmp);
	}

	@RequestMapping(value = "/testValueGet", method = RequestMethod.GET, produces = "application/json")
	public User testValueGet(@RequestBody @Valid User entity,
			HttpServletRequest request, HttpServletResponse response) {
		return entity;
	}
	
	@RequestMapping(value = "/testValuePost", method = RequestMethod.POST, produces = "application/json")
	public User testValuePost(@RequestBody @Valid User entity,
			HttpServletRequest request, HttpServletResponse response) {
		return entity;
	}
	
	@RequestMapping(value = "/testValueValid", method = RequestMethod.POST, produces = "application/json")
	public User testValueValid(@RequestBody  User entity,
			HttpServletRequest request, HttpServletResponse response) {
		return entity;
	}

	@RequestMapping("/send_email_test")
	public void sendEmail() {
		EmailMessage message = EmailMessage.create().subject("test email")
				.to("kostadin.popkocev@gmail.com").template("test_email.vm")
				.addToModel("name", "Име").addToModel("number", 12345);

		emailNotificationService.sendEmail(message);
	}

	@RequestMapping("/book")
	public void testBooking() {
		java.util.Date date = new java.util.Date();

		Doctor d = doctorService.findOne((long) 6);
		Patient p = patientService.findOne((long) 1);
		TimeSlots timeSlot = timeSlotsRepository.findOne((long) 1);

		// bookingService.bookAppointment(date, timeSlot, d);
	}
}