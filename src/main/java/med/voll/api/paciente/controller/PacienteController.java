package med.voll.api.paciente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.entidades.Paciente;
import med.voll.api.paciente.RepositoryPaciente;
import med.voll.api.paciente.dto.DadosBasicosPacienteDTO;
import med.voll.api.paciente.dto.PacienteAtualizarDTO;
import med.voll.api.paciente.dto.PacienteDTO;
import med.voll.api.paciente.dto.PacienteDetalheDTO;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
	
	@Autowired
	RepositoryPaciente repositoyPaciente;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid PacienteDTO dto, UriComponentsBuilder uriBuilder) {
		
		var paciente = repositoyPaciente.save(new Paciente(dto));
		var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new PacienteDetalheDTO(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosBasicosPacienteDTO>>  listarPacientes(Pageable paginacao) {
		
		var colecao = repositoyPaciente.findAllByAtivoTrue(paginacao).map(DadosBasicosPacienteDTO::new);
		
		return ResponseEntity.ok(colecao);
		
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid PacienteAtualizarDTO dto) {
		
		var paciente = repositoyPaciente.getReferenceById(dto.id());
		paciente.atualizar(dto);
		
		return ResponseEntity.ok(new PacienteDetalheDTO(paciente));
		
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var paciente = repositoyPaciente.getReferenceById(id);
		paciente.excluir();
		
		return ResponseEntity.noContent().build();
	}
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		
		var paciente = repositoyPaciente.getReferenceById(id);
		
		return ResponseEntity.ok(new PacienteDetalheDTO(paciente));
		
	}
}
