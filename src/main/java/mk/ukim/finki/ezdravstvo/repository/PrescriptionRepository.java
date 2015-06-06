package mk.ukim.finki.ezdravstvo.repository;

import java.util.List;

import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.model.Prescription;

public interface PrescriptionRepository extends
		JpaSpecificationRepository<Prescription> {

	
	List<Prescription> findByDiagnose(Diagnose diagnose);
}
