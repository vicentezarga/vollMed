package med.voll.api.medico.dto;

import med.voll.api.entidades.Medico;
import med.voll.api.shared.enums.Especialidade;

public record DadosBasicosMedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

	public DadosBasicosMedicoDTO(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}

}
