package mk.ukim.finki.ezdravstvo.service;

import java.util.List;

import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.model.Patient;

public interface DiagnoseService extends BaseEntityCrudService<Diagnose> {

	List<Diagnose> findByPatient(Patient patient);

}
