package mk.ukim.finki.ezdravstvo.web;

import java.sql.Date;

import mk.ukim.finki.ezdravstvo.model.AppointmentBooking;
import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.EmailMessage;
import mk.ukim.finki.ezdravstvo.repository.AppointmentBookingRepository;
import mk.ukim.finki.ezdravstvo.repository.TimeSlotsRepository;
import mk.ukim.finki.ezdravstvo.service.DoctorService;
import mk.ukim.finki.ezdravstvo.service.mail.EmailNotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	private DoctorService docService;

	@Autowired
	private AppointmentBookingRepository bookingRepository;

	@RequestMapping(value = "/addDoctor", method = RequestMethod.GET, produces = "application/json")
	public Doctor createDoctor() {
		Doctor tmp = new Doctor();
		tmp.setFistName("Name");
		tmp.setUsername("Doctor1");
		tmp = (Doctor) tmp;
		tmp.setPassword("qwe");
		return doctorService.saveAndFlush(tmp);
	}

	@RequestMapping("/send_email_test")
	public void sendEmail() {
		EmailMessage message = EmailMessage.create().subject("test email")
				.to("kostadin.popkocev@gmail.com").template("test_email.vm")
				.addToModel("name", "Име").addToModel("number", 12345);

		emailNotificationService.sendEmail(message);
	}

	@RequestMapping("/slots")
	public void testSlots() {
		System.out.println(timeSlotsRepository.count());
		System.out.println(timeSlotsRepository.findFreeSlots(new Date(150, 5,
				29), docService.findOne((long) 6)));
	}

	@RequestMapping("/book")
	public void testBooking() {
		Date d = new Date(150, 5, 29);
		AppointmentBooking ab = new AppointmentBooking();
		ab.setDate(d);

		bookingRepository.save(ab);
	}
}
