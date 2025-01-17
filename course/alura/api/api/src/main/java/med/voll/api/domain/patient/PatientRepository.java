package med.voll.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	Page<Patient> findAllActiveTrue(Pageable page);

	UserDetails findByLogin(String username);
}
