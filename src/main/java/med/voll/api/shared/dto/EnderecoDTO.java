package med.voll.api.shared.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
		
		@NotBlank(message = "Logradouro é obrigatório!")
		String logradouro, 
		
		@NotBlank(message = "Logradouro é obrigatório!")
		String bairro, 
		
		@NotBlank(message = "CEP é obrigatório!")
		@Pattern(regexp = "\\d{8}", message = "CEP informado em formato inválido!")
		String cep, 
		
		@NotBlank(message = "Cidade é obrigatório!")
		String cidade, 
		
		@NotBlank(message = "Estado é obrigatório!")
		String uf, 
		
		Integer numero,
		
		String complemento) {

}
