package med.voll.api.consulta.validacao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.dto.ConsultaDTO;
import med.voll.api.consulta.validacao.ValidadorAgendamentoConsulta;
import med.voll.api.medico.RepositoryMedico;
import med.voll.api.shared.infra.exception.ValidacaoException;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta{

	@Autowired
	private RepositoryMedico medicoRepository;

	public void validar(ConsultaDTO dados) {

		if (dados.idMedico() == null) {
			return;
		}

		var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());

		if (!medicoEstaAtivo) {
			throw new ValidacaoException ("Médico informado não está disponível!");
		}
	}

}
