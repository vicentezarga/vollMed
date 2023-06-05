
package med.voll.api.medico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.entidades.Medico;
import med.voll.api.medico.RepositoryMedico;
import med.voll.api.medico.dto.DadosBasicosMedicoDTO;
import med.voll.api.medico.dto.DadosDetalhadosMedicoDTO;
import med.voll.api.medico.dto.MedicoAtualizarDTO;
import med.voll.api.medico.dto.MedicoDTO;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key") 
public class MedicoController {

	@Autowired
	RepositoryMedico repositoryMedico;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid MedicoDTO dados, UriComponentsBuilder uriBuilder) {
		
		var  medico = new Medico(dados);
		
		repositoryMedico.save(medico);
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhadosMedicoDTO(medico));
	}

	@GetMapping
	public ResponseEntity<List<DadosBasicosMedicoDTO>>  listarMedicos() {

		var colecao = repositoryMedico.findAll().stream().map(DadosBasicosMedicoDTO::new).toList();
		
		return ResponseEntity.ok(colecao);
	}

    @GetMapping("paginado")
    public ResponseEntity<Page<DadosBasicosMedicoDTO>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        
    	var page = repositoryMedico.findAllByAtivoTrue(paginacao).map(DadosBasicosMedicoDTO::new);
    	
    	return ResponseEntity.ok(page);
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhadosMedicoDTO> atualizar(@RequestBody @Valid MedicoAtualizarDTO dto) {
    	
    	var medico = repositoryMedico.getReferenceById(dto.id());
    	medico.atualizarMedico(dto);
    	
    	return ResponseEntity.ok(new DadosDetalhadosMedicoDTO(medico));
    	
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
    	repositoryMedico.deleteById(id);
    	
    	return ResponseEntity.noContent().build();
    }
   
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity excluirLogicamente(@PathVariable Long id) {
    	var medico = repositoryMedico.getReferenceById(id);
    	medico.excluir();
    	
    	return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")//Apenas perfil admin pode detalhar um médico(para anotar todo controller definir na assinatura da classe)
    public ResponseEntity detalhar(@PathVariable Long id) {
    	
    	var medico = repositoryMedico.getReferenceById(id);
    	
    	return ResponseEntity.ok(new DadosDetalhadosMedicoDTO(medico));
    	
    }
    
}
