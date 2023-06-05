package med.voll.api.consulta.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.consulta.dao.RepositoryConsulta;
import med.voll.api.consulta.dto.CancelamentoConsultaDetalhamentoDTO;
import med.voll.api.consulta.dto.CancelarConsultaDTO;
import med.voll.api.consulta.dto.ConsultaDTO;
import med.voll.api.consulta.dto.ConsultaDetalhamentoDTO;
import med.voll.api.consulta.validacao.ValidadorAgendamentoConsulta;
import med.voll.api.consulta.validacao.ValidarCancelamentoConsulta;
import med.voll.api.entidades.Consulta;
import med.voll.api.entidades.Medico;
import med.voll.api.medico.RepositoryMedico;
import med.voll.api.paciente.RepositoryPaciente;
import med.voll.api.shared.infra.exception.ValidacaoException;

@Service
public class ConsultaService {

	@Autowired
	private RepositoryConsulta consultaDAO;

	@Autowired
	private RepositoryMedico medicoDAO;

	@Autowired
	private RepositoryPaciente pacienteDAO;

	@Autowired
	private List<ValidadorAgendamentoConsulta> validadoresMarcacao;
	
	@Autowired
	private List<ValidarCancelamentoConsulta> validadoresCancelamento;
	
	public ConsultaDetalhamentoDTO agendar(ConsultaDTO dados) {

		if (dados.idMedico() != null && !medicoDAO.existsById(dados.idMedico())) {
			throw new ValidacaoException("Id do médico informado não existe!");
		}

		if (!pacienteDAO.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Id do Paciente informado não existe!");
		}
		
		validadoresMarcacao.forEach(v -> v.validar(dados));

		var medico = escolherMedico(dados);

		if(medico==null) {
			throw new ValidacaoException("Não temos médicos disponíveis neste período!");
		}
		
		var paciente = pacienteDAO.getReferenceById(dados.idPaciente());
		var codigo = "C" + LocalDate.now().getYear() + (consultaDAO.getProximoCodigo() + 1);

		var consulta = new Consulta(codigo, medico, paciente, dados.data(),true);

		return new ConsultaDetalhamentoDTO(consultaDAO.save(consulta));
		
	}

	public CancelamentoConsultaDetalhamentoDTO cancelarConsulta(CancelarConsultaDTO dados) {

		if(!consultaDAO.exists(dados.codigoConsulta())) {
			throw new ValidacaoException("Consulta não encontrada!");
		}
		
		validadoresCancelamento.forEach(v -> v.validar(dados));
		
		var consulta = consultaDAO.findbyCodigo(dados.codigoConsulta());
		consulta.cancelarConsulta(dados.motivo());	

		return new CancelamentoConsultaDetalhamentoDTO(consulta);
	}

	private Medico escolherMedico(ConsultaDTO dados) {

		if (dados.idMedico() != null) {
			return medicoDAO.findById(dados.idMedico()).get();
		}
		if (dados.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
		}

		return medicoDAO.escolherMidicoAleatorioLivreNaData(dados.especialidade(), dados.data());
	}
}
