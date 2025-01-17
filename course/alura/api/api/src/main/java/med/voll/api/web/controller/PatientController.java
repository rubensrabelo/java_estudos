package med.voll.api.web.controller;

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
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientListData;
import med.voll.api.domain.patient.PatientRegisterData;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.PatientUpdateData;

@RestController
@RequestMapping("/patients")
public class PatientController {
	
	@Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PatientRegisterData data) {
        repository.save(new Patient(data));
    }

    @GetMapping
    public Page<PatientListData> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable page) {
        return repository.findAllActiveTrue(page).map(PatientListData::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid PatientUpdateData data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.desable();
    }
}
