package med.voll.api.domain.doctor;

public record DataListMedic(Long id, String name, String email, String phoneNumber, String crm, Specialty specialty) {
	
	public DataListMedic(Medic medic) {
		this(medic.getId(), medic.getName(), medic.getEmail(), medic.getPhoneNumber(), medic.getCrm(),
				medic.getSpecialty());
	}
}
