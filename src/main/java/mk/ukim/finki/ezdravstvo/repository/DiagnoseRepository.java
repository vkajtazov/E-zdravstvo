package mk.ukim.finki.ezdravstvo.repository;

import java.util.List;

import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.model.Patient;

public interface DiagnoseRepository extends
		JpaSpecificationRepository<Diagnose> {

	List<Diagnose> findByPatient(Patient patient);
}
