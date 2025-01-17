package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface MedicRepository extends JpaRepository<Medic, Long> {
	Page<Medic> findAllByActiveTrue(Pageable page);

	UserDetails findByLogin(String username);
}
