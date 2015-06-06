package mk.ukim.finki.ezdravstvo.service;

import java.util.List;

import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.model.Prescription;

public interface PrescriptionService extends
		BaseEntityCrudService<Prescription> {

	List<Prescription> findByDiagnose(Diagnose diagnose);

}
