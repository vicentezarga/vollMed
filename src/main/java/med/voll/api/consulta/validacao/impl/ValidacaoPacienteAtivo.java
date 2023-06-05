package med.voll.api.consulta.validacao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.dto.ConsultaDTO;
import med.voll.api.consulta.validacao.ValidadorAgendamentoConsulta;
import med.voll.api.paciente.RepositoryPaciente;
import med.voll.api.shared.infra.exception.ValidacaoException;

@Component
public class ValidacaoPacienteAtivo implements ValidadorAgendamentoConsulta{

	@Autowired
	private RepositoryPaciente repositoryPaciente;

	public void validar(ConsultaDTO dados) {

		var pacienteAtivo = repositoryPaciente.findAllByAtivoTrue(dados.idPaciente());
		
		if(!pacienteAtivo) {
			throw new ValidacaoException("Paciente não está ativo!");
		}
	}
}
