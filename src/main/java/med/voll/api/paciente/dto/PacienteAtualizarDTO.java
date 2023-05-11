package med.voll.api.paciente.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.shared.dto.EnderecoDTO;

public record PacienteAtualizarDTO(
		
		@NotNull
		Long id, 
		
		String nome, 
		
		String telefone, 
		
		EnderecoDTO endereco) {

}
