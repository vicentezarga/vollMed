package med.voll.api.mensageria.dto;

import jakarta.validation.constraints.NotBlank;

public record MessageDTO(
		
	@NotBlank 
	String mensagem
	
	){
}
