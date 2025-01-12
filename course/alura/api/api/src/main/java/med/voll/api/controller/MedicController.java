package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.DataListMedic;
import med.voll.api.doctor.DataRegistrationMedic;
import med.voll.api.doctor.DataUpdateMedic;
import med.voll.api.doctor.Medic;
import med.voll.api.doctor.MedicRepository;

@RestController
@RequestMapping("/medics")
public class MedicController {
	
	@Autowired
	private MedicRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DataRegistrationMedic data) {
		repository.save(new Medic(data));
	}

	@GetMapping
	public Page<DataListMedic> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable page) {
		return repository.findAllByActiveTrue(page).map(DataListMedic::new);
	}

	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DataUpdateMedic data) {
		var medic = repository.getReferenceById(data.id());
		medic.updateData(data);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		var medic = repository.getReferenceById(id);
		medic.disable();
	}
}
