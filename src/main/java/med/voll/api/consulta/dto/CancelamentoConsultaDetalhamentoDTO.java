package med.voll.api.consulta.dto;

import med.voll.api.entidades.Consulta;

public record CancelamentoConsultaDetalhamentoDTO(String codigoConsulta, String Motivo) {

	public CancelamentoConsultaDetalhamentoDTO(Consulta consulta) {
		this(consulta.getCodigo(), consulta.getMotivoCancelamento().getDescricao());
	}
	
}
