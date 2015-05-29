package mk.ukim.finki.ezdravstvo.service;

import mk.ukim.finki.ezdravstvo.model.Patient;
public interface PatientService extends BaseEntityCrudService<Patient>{

	Patient findByUsername(String username);

}
