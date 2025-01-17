package med.voll.api.domain.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import med.voll.api.domain.doctor.MedicRepository;
import med.voll.api.domain.patient.PatientRepository;

@Service
public class AuthService implements UserDetailsService {
	
	private final MedicRepository medicRepository;
	private final PatientRepository patientRepository;

	public AuthService(MedicRepository medicRepository, PatientRepository patientRepository) {
		this.medicRepository = medicRepository;
		this.patientRepository = patientRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.length() == 11)
			return patientRepository.findByLogin(username);
		return medicRepository.findByLogin(username);
	}

}
