package med.voll.api.consulta.validacao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.dao.RepositoryConsulta;
import med.voll.api.consulta.dto.ConsultaDTO;
import med.voll.api.consulta.validacao.ValidadorAgendamentoConsulta;
import med.voll.api.shared.infra.exception.ValidacaoException;

@Component
public class ValidacaoPacienteConsultaMesmoHorario implements ValidadorAgendamentoConsulta {

	@Autowired
	private RepositoryConsulta repositoryConsulta;

	public void validar(ConsultaDTO dto) {

		var primeiroHorario = dto.data().withHour(7);
		var ultimoHorario = dto.data().withHour(18);

		var pacienteTemConsultaPeriodo = repositoryConsulta.existsByPacienteIdAndDataBetween(dto.idPaciente(),
				primeiroHorario, ultimoHorario);

		if (pacienteTemConsultaPeriodo) {
			
			throw new ValidacaoException("Paciente já possuí consulta marcada para o dia!");
		}
	}
}
