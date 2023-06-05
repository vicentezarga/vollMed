package med.voll.api.consulta.validacao.impl;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.consulta.dto.ConsultaDTO;
import med.voll.api.consulta.validacao.ValidadorAgendamentoConsulta;
import med.voll.api.shared.infra.exception.ValidacaoException;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {

	public void validar(ConsultaDTO dados) {

		var dataConsulta = dados.data();
		var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var dtAbertura = dataConsulta.getHour() < 7;
		var dtEncerramento = dataConsulta.getHour() > 18;
		var dtAtual = LocalDateTime.now();
		var dtDiferenca = Duration.between(dtAtual, dataConsulta).toMinutes();
		
		if (domingo || dtAbertura || dtEncerramento) {
			throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica (segunda a sexta das 08:00 às 19:00).");
		}
		if(dtDiferenca<30){
			throw new ValidacaoException("Consulta deve ser agendada com 30 minutos de antecedência!");
		}
		
	}
}
