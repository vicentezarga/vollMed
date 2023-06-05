package med.voll.api.consulta.dto;

import java.time.LocalDateTime;

import med.voll.api.entidades.Consulta;

public record ConsultaDetalhamentoDTO(Long id, Long idPaciente, Long IdMedico, LocalDateTime data) {

	public ConsultaDetalhamentoDTO(Consulta consulta) {
		this(consulta.getId(), consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getData());
	}
	
}
