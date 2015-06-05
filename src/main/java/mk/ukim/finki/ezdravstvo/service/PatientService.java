package mk.ukim.finki.ezdravstvo.service;

import java.util.List;

import mk.ukim.finki.ezdravstvo.model.Doctor;
import mk.ukim.finki.ezdravstvo.model.Patient;
public interface PatientService extends BaseEntityCrudService<Patient>{

	Patient findByUsername(String username);

	List<Patient> findByDoctor(Doctor doctor);
}
