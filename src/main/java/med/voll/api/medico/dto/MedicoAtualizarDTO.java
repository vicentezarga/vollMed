package med.voll.api.medico.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.shared.dto.EnderecoDTO;

public record MedicoAtualizarDTO(
		
		@NotNull
		Long id,
		
		String nome, 
		
		String telefone, 
		
		EnderecoDTO endereco) {

}
