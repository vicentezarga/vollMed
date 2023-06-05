package med.voll.api.consulta.validacao.impl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.dao.RepositoryConsulta;
import med.voll.api.consulta.dto.CancelarConsultaDTO;
import med.voll.api.consulta.validacao.ValidarCancelamentoConsulta;
import med.voll.api.shared.infra.exception.ValidacaoException;

@Component
public class ValidarCancelamentoConsultaPeriodo implements ValidarCancelamentoConsulta {

	@Autowired
	private RepositoryConsulta consultaRepository;
	
	public void validar(CancelarConsultaDTO dados) {

		var dtConsulta = consultaRepository.findDataConsultaByCodigo(dados.codigoConsulta());
		var dtAtual = LocalDateTime.now();
		var antecedencia = Duration.between(dtAtual, dtConsulta).toHours();
		
		if(antecedencia <= 24) {
			throw new ValidacaoException("Consulta só pode ser cancelada com 24 horas de antecedência!");
		}
		
	}
}
