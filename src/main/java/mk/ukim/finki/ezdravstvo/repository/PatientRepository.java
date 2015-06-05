package mk.ukim.finki.ezdravstvo.repository;

import java.util.List;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Patient;

public interface PatientRepository extends JpaSpecificationRepository<Patient> {

	Patient findByUsername(String username);
	
	List<Patient> findByPrimaryDoctor(Doctor doctor);
}
