package med.voll.api.consulta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.shared.enums.MotivoCancelamento;

public record CancelarConsultaDTO(
		
		@NotBlank
		String codigoConsulta, 
		
		@NotNull
		MotivoCancelamento motivo
		
		){
}
