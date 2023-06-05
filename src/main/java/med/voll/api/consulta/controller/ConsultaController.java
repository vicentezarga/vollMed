package med.voll.api.consulta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.consulta.dto.CancelarConsultaDTO;
import med.voll.api.consulta.dto.ConsultaDTO;
import med.voll.api.consulta.service.ConsultaService;

@RestController
@RequestMapping("/consulta")
@SecurityRequirement(name = "bearer-key") 
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	
	@PostMapping
	@Transactional
	public ResponseEntity agendar(@RequestBody @Valid ConsultaDTO dados) {
		
		var consulta = consultaService.agendar(dados);

		return ResponseEntity.ok(consulta);
	}
	@PutMapping
	@Transactional
	public ResponseEntity cancelarConsulta(@RequestBody @Valid CancelarConsultaDTO dados) {
		
		var dto = consultaService.cancelarConsulta(dados);
		
		return ResponseEntity.ok(dto);
	}
	
}
