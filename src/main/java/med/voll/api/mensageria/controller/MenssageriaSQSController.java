package med.voll.api.mensageria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import med.voll.api.medico.dto.MedicoDTO;
import med.voll.api.mensageria.service.MessageService;

@RestController
@RequestMapping("menssageriaSQSController")
public class MenssageriaSQSController {

	@Autowired
	private MessageService messageService;

	@PostMapping
	public ResponseEntity sendMessage(@RequestBody @Valid MedicoDTO medico) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		String medicoJson = mapper.writeValueAsString(medico);

		messageService.sendMessage(medicoJson);

		return ResponseEntity.ok("Message Send");
	}
}
