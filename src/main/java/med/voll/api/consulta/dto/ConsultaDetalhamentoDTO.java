package med.voll.api.consulta.dto;

import java.time.LocalDateTime;

import med.voll.api.entidades.Consulta;
import med.voll.api.shared.enums.Especialidade;

public record ConsultaDetalhamentoDTO(Long id, Long idPaciente, Long IdMedico,Especialidade especialidade ,LocalDateTime data) {

	public ConsultaDetalhamentoDTO(Consulta consulta) {
		this(consulta.getId(), consulta.getPaciente().getId(), consulta.getMedico().getId(),consulta.getMedico().getEspecialidade(), consulta.getData());
	}
	
}
