package med.voll.api.consulta.validacao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.dao.RepositoryConsulta;
import med.voll.api.consulta.dto.ConsultaDTO;
import med.voll.api.consulta.validacao.ValidadorAgendamentoConsulta;
import med.voll.api.shared.infra.exception.ValidacaoException;

@Component
public class ValidadorMedicoConsultaMesmoHorario implements ValidadorAgendamentoConsulta {

	@Autowired
	private RepositoryConsulta consultaRepository;

	public void validar(ConsultaDTO dados) {

		var medicoTemConsulta = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

		if(medicoTemConsulta) {
			throw new ValidacaoException("Médico já possuí consulta marcada para o horário");
		}
	}

}
