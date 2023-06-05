package med.voll.api.consulta.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.shared.enums.Especialidade;

public record ConsultaDTO(

		@NotNull
		@JsonAlias({"paciente"})
		Long idPaciente,

		@JsonAlias({"medico"})
		Long idMedico,

		@NotNull
		@Future
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
		LocalDateTime data,
		
		Especialidade especialidade){
}
