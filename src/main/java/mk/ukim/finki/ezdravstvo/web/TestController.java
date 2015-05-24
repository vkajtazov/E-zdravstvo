package mk.ukim.finki.ezdravstvo.web;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping(value="/addDoctor", method = RequestMethod.GET, produces = "application/json")
	public Doctor createDoctor(){
		Doctor tmp = new Doctor();
		tmp.setFistName("Name");
		tmp.setUsername("Doctor1");
		tmp = (Doctor) tmp;
		tmp.setPassword("qwe");
		return doctorService.saveAndFlush(tmp);
	}
}
