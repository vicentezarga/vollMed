package med.voll.api.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.shared.dto.EnderecoDTO;
import med.voll.api.shared.enums.Especialidade;

public record MedicoDTO(
		
		@NotBlank(message = "{nome.obrigatorio}")
		String nome,
		
		@NotBlank(message = "{email.obrigatorio}")
		@Email(message = "{email.invalido}")
		String email, 
		
		@NotBlank(message = "{telefone.obrigatorio}")
		@Pattern(regexp = "\\d{11}", message = "Formato telefone inválido!")
		String telefone,
		
		@NotBlank(message = "{crm.obrigatorio}")
	    @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
		String crm, 
		
		@NotNull(message = "{especialidade.obrigatoria}")
		Especialidade especialidade,
		
		@NotNull(message = "{endereco.obrigatorio}")
		@Valid
		EnderecoDTO endereco) {
	
}
